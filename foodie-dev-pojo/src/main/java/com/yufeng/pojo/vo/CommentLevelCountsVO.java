package com.yufeng.pojo.vo;

import lombok.Data;

/**
 * 描述:
 *      用于展示商品评价数量的vo
 * @author yufeng
 * @create 2020-10-26
 */
@Data
public class CommentLevelCountsVO {

    private Integer totalCounts;


    private Integer goodCounts;


    private Integer normalCounts;


    private Integer badCounts;

}
