package com.yufeng.service;

import com.yufeng.pojo.OrderStatus;
import com.yufeng.pojo.bo.SubmitOrderBO;
import com.yufeng.pojo.vo.OrderVO;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-02
 */
public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO
     */
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);


    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId, Integer orderStatus);


    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    public OrderStatus queryOrderStatusInfo(String orderId);


}
