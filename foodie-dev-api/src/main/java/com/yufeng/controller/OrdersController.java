package com.yufeng.controller;

import com.yufeng.enums.OrderStatusEnum;
import com.yufeng.enums.PayMethod;
import com.yufeng.pojo.OrderStatus;
import com.yufeng.pojo.bo.SubmitOrderBO;
import com.yufeng.pojo.vo.MerchantOrdersVO;
import com.yufeng.pojo.vo.OrderVO;
import com.yufeng.service.OrderService;
import com.yufeng.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-02
 */

@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create(@RequestBody SubmitOrderBO submitOrderBO, HttpServletRequest request, HttpServletResponse response){

        System.out.println(submitOrderBO.toString());

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.getType()
                && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.getType()) {
            return IMOOCJSONResult.errorMsg("支付方式不支持!");
        }

        // 1. 创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();



        // 2. 创建订单以后, 移除购物车中已结算(已提交)的商品
        /**
         * 1001
         * 2002 -> 用户购买
         * 3003 -> 用户购买
         * 4004
         */
        //TODO 整合redis之后, 完善购物车中的已结算商品清除, 并且同步到前端的cookie
        //CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        // 3. 向支付中心发送当前订单, 用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        // 为了方便测试购买, 所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("imoocUserId", "imooc");
        httpHeaders.add("password", "imooc");

        HttpEntity<MerchantOrdersVO> httpEntity = new HttpEntity<>(merchantOrdersVO, httpHeaders);

        ResponseEntity<IMOOCJSONResult>  responseEntity = restTemplate.postForEntity(paymentUrl, httpEntity, IMOOCJSONResult.class);
        IMOOCJSONResult paymentResult = responseEntity.getBody();
        if (paymentResult.getStatus() != 200) {
            return IMOOCJSONResult.errorMsg("支付中心订单创建失败, 请联系管理员!");
        }

        return IMOOCJSONResult.ok(orderId);     // orderId 用于生成二维码
    }


    /**
     * (构建商户端支付成功的回调接口)
     * 由支付中心通知自己的商户后台系统
     * @param merchantOrderId
     * @return
     */
    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {

        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }


    @PostMapping("getPaidOrderInfo")
    public IMOOCJSONResult getPaidOrderInfo(String orderId) {

        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return IMOOCJSONResult.ok(orderStatus);
    }


}
