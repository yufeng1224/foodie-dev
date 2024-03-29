package com.yufeng.controller;

import com.yufeng.pojo.Orders;
import com.yufeng.service.center.MyOrdersService;
import com.yufeng.utils.IMOOCJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-27
 */
@Controller
public class BaseController {

    @Autowired
    public MyOrdersService myOrdersService;

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    public static final String FOODIE_SHOPCART = "shopcart";

    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";


    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    // 回调通知的url(内网穿透的地址)
    String payReturnUrl = "http://localhost:8088/orders/notifyMerchantOrderPaid";


    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    // 回调通知的url(线上的地址)
    //String payReturnUrl = "http://api.z.mukewang.com:8088/foodie-dev-api/orders/notifyMerchantOrderPaid";


    // 用户上传头像的位置
    /**
     * 如果以后有测试环境、预发布环境、生产环境。 这样配置很不好
     * 解决方案: 通过资源文件来配置
     */
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspaces" +
                                                          File.separator + "images" +
                                                          File.separator + "foodie" +
                                                          File.separator + "faces";


    /**
     * 用于验证用户和订单是否有关联关系, 避免非法用户调用
     * @return
     */
    public IMOOCJSONResult checkUserOrder(String userId, String orderId) {

        Orders orders = myOrdersService.queryMyOrder(userId, orderId);
        if (orders == null) {
            return IMOOCJSONResult.errorMsg("订单不存在");
        }
        return IMOOCJSONResult.ok(orders);

    }
}




















