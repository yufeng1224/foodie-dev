package com.yufeng.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.yufeng.enums.OrderStatusEnum;
import com.yufeng.enums.YesOrNo;
import com.yufeng.mapper.OrderStatusMapper;
import com.yufeng.mapper.OrdersMapper;
import com.yufeng.mapper.OrdersMapperCustom;
import com.yufeng.pojo.OrderStatus;
import com.yufeng.pojo.Orders;
import com.yufeng.pojo.vo.MyOrdersVO;
import com.yufeng.pojo.vo.OrderStatusCountsVO;
import com.yufeng.service.center.MyOrdersService;
import com.yufeng.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-08
 */
@Service
public class MyOrdersServiceImpl extends BaseService implements MyOrdersService {

    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private OrdersMapper ordersMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);
        List<MyOrdersVO> list = ordersMapperCustom.queryMyOrders(map);

        return setterPageGrid(list, page);
    }


//    private PagedGridResult setterPageGrid(List<?> list, Integer page) {
//        PageInfo<?> pageList = new PageInfo<>(list);
//        PagedGridResult gridResult = new PagedGridResult();
//        gridResult.setPage(page);
//        gridResult.setRows(list);
//        gridResult.setRecords(pageList.getTotal());
//        gridResult.setTotal(pageList.getPages());
//        return gridResult;
//    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateDeliverOrderStatus(String orderId) {

        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        updateOrder.setDeliverTime(new Date());

        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);

        orderStatusMapper.updateByExampleSelective(updateOrder, example);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Orders queryMyOrder(String userId, String orderId) {

        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setId(orderId);
        orders.setIsDelete(YesOrNo.NO.type);

        return ordersMapper.selectOne(orders);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateReceiveOrderStatus(String orderId) {

        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        updateOrder.setSuccessTime(new Date());

        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);

        int result = orderStatusMapper.updateByExampleSelective(updateOrder, example);
        return result == 1 ? true : false;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteOrder(String userId, String orderId) {

        Orders updateOrder = new Orders();
        updateOrder.setIsDelete(YesOrNo.YES.type);
        updateOrder.setUpdatedTime(new Date());

        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", orderId);
        criteria.andEqualTo("userId", userId);

        int result = ordersMapper.updateByExampleSelective(updateOrder, example);
        return result == 1 ? true : false;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("orderStatus", OrderStatusEnum.WAIT_PAY.type);
        int waitPayCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);
        int waitDeliverCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);
        int waitReceiveCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.SUCCESS.type);
        map.put("isComment", YesOrNo.NO.type);
        int waitCommentCounts = ordersMapperCustom.getMyOrderStatusCounts(map);

        OrderStatusCountsVO countsVO = new OrderStatusCountsVO(waitPayCounts, waitDeliverCounts, waitReceiveCounts, waitCommentCounts);

        return countsVO;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<OrderStatus> list = ordersMapperCustom.getMyOrderTrend(map);
        return setterPageGrid(list, page);
    }
}
