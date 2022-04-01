package com.yufeng.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.yufeng.enums.YesOrNo;
import com.yufeng.mapper.ItemsCommentsMapperCustom;
import com.yufeng.mapper.OrderItemsMapper;
import com.yufeng.mapper.OrderStatusMapper;
import com.yufeng.mapper.OrdersMapper;
import com.yufeng.pojo.OrderItems;
import com.yufeng.pojo.OrderStatus;
import com.yufeng.pojo.Orders;
import com.yufeng.pojo.bo.center.OrderItemsCommentBO;
import com.yufeng.pojo.vo.MyCommentVO;
import com.yufeng.service.center.MyCommentsService;
import com.yufeng.utils.PagedGridResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-09
 */
@Service
public class MyCommentsServiceImpl extends  BaseService implements MyCommentsService {

    @Autowired
    private Sid sid;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    public OrderItemsMapper orderItemsMapper;

    @Autowired
    public OrderStatusMapper orderStatusMapper;

    @Autowired
    public ItemsCommentsMapperCustom itemsCommentsMapperCustom;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems query = new OrderItems();
        query.setOrderId(orderId);
        return orderItemsMapper.select(query);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentBOList) {

        // 1. 保存评价 items_comments
        for (OrderItemsCommentBO itemsCommentBO : commentBOList) {
            itemsCommentBO.setCommentId(sid.nextShort());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentList", commentBOList);
        itemsCommentsMapperCustom.saveComments(map);

        // 2. 修改订单表, 改为已评价 orders
        Orders order = new Orders();
        order.setId(orderId);
        order.setIsComment(YesOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(order);

        // 3. 修改订单状态表的留言时间 order_status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<MyCommentVO> myCommentVOList = itemsCommentsMapperCustom.queryMyComments(map);
        return setterPageGrid(myCommentVOList, page);
    }
}
