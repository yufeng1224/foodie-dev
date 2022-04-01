package com.yufeng.service.center;

import com.yufeng.pojo.OrderItems;
import com.yufeng.pojo.bo.center.OrderItemsCommentBO;
import com.yufeng.utils.PagedGridResult;

import java.util.List;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-09
 */
public interface MyCommentsService {


    /**
     * 根据订单id查询关联的商品
     * @param orderId
     * @return
     */
    public List<OrderItems> queryPendingComment(String orderId);


    /**
     * 保存用户的评论
     * @param orderId
     * @param userId
     * @param commentBOList
     */
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentBOList);


    /**
     * 我的评价查询 分页
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);

}
