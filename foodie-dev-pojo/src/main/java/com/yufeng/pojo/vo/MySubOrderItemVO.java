package com.yufeng.pojo.vo;

import lombok.Data;

/**
 * 描述:
 *      用户中心, 我的订单列表嵌套商品VO
 * @author yufeng
 * @create 2020-12-08
 */
@Data
public class MySubOrderItemVO {

    private String itemId;

    private String itemImg;

    private String itemName;

    private String itemSpecName;

    private Integer buyCounts;

    private Integer price;

}
