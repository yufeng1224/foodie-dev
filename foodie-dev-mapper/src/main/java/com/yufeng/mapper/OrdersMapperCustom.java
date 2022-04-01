package com.yufeng.mapper;

import com.yufeng.pojo.OrderStatus;
import com.yufeng.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *      订单自定义mapper接口
 * @author yufeng
 * @create 2020-12-08
 */
public interface OrdersMapperCustom {

    public List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);


    public int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);


    public List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);

}
