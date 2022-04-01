package com.yufeng.controller.center;

import com.yufeng.controller.BaseController;
import com.yufeng.pojo.vo.OrderStatusCountsVO;
import com.yufeng.utils.IMOOCJSONResult;
import com.yufeng.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-08
 */
@Api(value = "用户中心我的订单", tags = {"用户中心我们的点单相关接口"})
@RestController
@RequestMapping("myOrders")
public class MyOrdersController extends BaseController  {

//    @Autowired
//    private MyOrdersService myOrdersService;

    @ApiOperation(value = "获得订单状态数概况", notes = "获得订单状态数概况", httpMethod = "POST")
    @PostMapping("/statusCounts")
    public IMOOCJSONResult statusCounts (
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        OrderStatusCountsVO result = myOrdersService.getOrderStatusCounts(userId);
        return IMOOCJSONResult.ok(result);
    }


    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/query")
    public IMOOCJSONResult query (
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderStatus", value = "订单状态")
            @RequestParam Integer orderStatus,
            @ApiParam(name = "page", value = "查询下一页的第几页")
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "每一页显示的条数")
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null ) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        /** 分页插件不支持嵌套结果映射 */
        PagedGridResult grid  = myOrdersService.queryMyOrders(userId, orderStatus, page, pageSize);
        return IMOOCJSONResult.ok(grid);
    }


    // 商家发货没有后端, 这个接口仅仅用于模拟
    @ApiOperation(value = "商家发货", notes = "商家发货", httpMethod = "GET")
    @GetMapping("/deliver")
    public IMOOCJSONResult deliver(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {

        if (StringUtils.isBlank(orderId)) {
            return IMOOCJSONResult.errorMsg("订单ID不能为空");
        }

        myOrdersService.updateDeliverOrderStatus(orderId);
        return IMOOCJSONResult.ok();
    }


    @ApiOperation(value = "用户确认收货", notes = "用户确认收货", httpMethod = "POST")
    @GetMapping("/confirmReceive")
    public IMOOCJSONResult confirmReceive(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {

        IMOOCJSONResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        boolean res = myOrdersService.updateReceiveOrderStatus(orderId);
        if (!res) {
            return IMOOCJSONResult.errorMsg("订单确认收货失败! ");
        }

        return IMOOCJSONResult.ok();
    }


    /**
     *  虽然有一部分代码是完全一样的, 但是绝对不建议 确认和删除合并为同一个接口!
     *  代码的耦合度就大大增加了!
     *  如果后期有一些各自的业务的话, 里面的代码会越来越多，耦合度也越来越大!!!
     *
     * @param orderId
     * @param userId
     * @return
     */
    @ApiOperation(value = "用户删除订单", notes = "用户删除订单", httpMethod = "POST")
    @GetMapping("/delete")
    public IMOOCJSONResult delete(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {

        IMOOCJSONResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        boolean res = myOrdersService.deleteOrder(userId, orderId);
        if (!res) {
            return IMOOCJSONResult.errorMsg("订单删除失败! ");
        }

        return IMOOCJSONResult.ok();
    }


    @ApiOperation(value = "查询订单动向", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/trend")
    public IMOOCJSONResult trend (
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "page", value = "查询下一页的第几页")
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "每一页显示的条数")
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null ) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        /** 分页插件不支持嵌套结果映射 */
        PagedGridResult grid  = myOrdersService.getOrdersTrend(userId, page, pageSize);
        return IMOOCJSONResult.ok(grid);
    }


}
