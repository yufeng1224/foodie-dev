package com.yufeng.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-10
 */
@Data
public class MyCommentVO {

    private String commentId;

    private String content;

    private Date createTime;

    private String itemId;

    private String itemName;

    private String specName;

    private String itemImg;

}
