package com.yufeng.service.center;

import com.yufeng.pojo.Orders;
import com.yufeng.pojo.vo.OrderStatusCountsVO;
import com.yufeng.utils.PagedGridResult;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-08
 */
public interface MyOrdersService {

    /**
     * 查询我的订单列表
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);


    /**
     * 订单状态 ----> 商家发货
     * @param orderId
     */
    public void updateDeliverOrderStatus(String orderId);


    /**
     * 查询我的订单
     * @param userId
     * @param orderId
     * @return
     */
    public Orders queryMyOrder(String userId, String orderId);


    /**
     * 更新订单状态 ----> 确认收货
     * @param orderId
     * @return
     */
    public boolean updateReceiveOrderStatus(String orderId);


    /**
     * 删除订单(逻辑删除)
     * @param userId
     * @param orderId
     * @return
     */
    public boolean deleteOrder(String userId, String orderId);


    /**
     * 查询用户订单数
     * @param userId
     */
    public OrderStatusCountsVO getOrderStatusCounts(String userId);


    /**
     * 获得分页的订单动向
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize);

}
