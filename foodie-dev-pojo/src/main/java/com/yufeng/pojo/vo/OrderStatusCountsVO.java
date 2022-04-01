package com.yufeng.pojo.vo;

import lombok.Data;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-10
 */
@Data
public class OrderStatusCountsVO {

    private Integer waitPayCounts;

    private Integer waitDeliverCounts;

    private Integer waitReceiveCounts;

    private Integer waitCommentCounts;

    public OrderStatusCountsVO() {

    }

    public OrderStatusCountsVO(Integer waitPayCounts, Integer waitDeliverCounts, Integer waitReceiveCounts, Integer waitCommentCounts) {
        this.waitPayCounts = waitPayCounts;
        this.waitDeliverCounts = waitDeliverCounts;
        this.waitReceiveCounts = waitReceiveCounts;
        this.waitCommentCounts = waitCommentCounts;
    }
}
