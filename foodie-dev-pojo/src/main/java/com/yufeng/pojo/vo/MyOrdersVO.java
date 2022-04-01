package com.yufeng.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *      用户中心, 我的订单列表VO
 * @author yufeng
 * @create 2020-12-08
 */
@Data
public class MyOrdersVO {

    private String orderId;

    private Date createdTime;

    private Integer payMethod;

    private Integer realPayAmount;

    private Integer postAmount;

    private Integer isComment;

    private Integer orderStatus;

    private List<MySubOrderItemVO> subOrderItemList;

}
