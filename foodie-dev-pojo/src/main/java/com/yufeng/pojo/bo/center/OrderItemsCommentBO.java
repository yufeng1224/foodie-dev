package com.yufeng.pojo.bo.center;

import lombok.Data;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-10
 */
@Data
public class OrderItemsCommentBO {

    private String commentId;

    private String itemId;

    private String itemName;

    private String itemSpecId;

    private String itemSpecName;

    private Integer commentLevel;

    private String content;

}
