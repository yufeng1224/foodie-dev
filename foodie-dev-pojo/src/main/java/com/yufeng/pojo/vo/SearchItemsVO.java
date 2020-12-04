package com.yufeng.pojo.vo;

import lombok.Data;

/**
 * 描述:
 *      用于展示商品搜索列表结果的VO
 * @author yufeng
 * @create 2020-10-27
 */

@Data
public class SearchItemsVO {

    private String itemId;

    private String itemName;

    private int sellCounts;

    private String imgUrl;

    /**
     * 以分为单位, 直接发送给前端，由前端来进行除以100
     */
    private int price;


}
