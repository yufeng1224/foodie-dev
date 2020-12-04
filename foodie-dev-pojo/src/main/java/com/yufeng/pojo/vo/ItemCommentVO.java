package com.yufeng.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * 描述:
 *      用于展示商品评价的VO
 * @author yufeng
 * @create 2020-10-27
 */
@Data
public class ItemCommentVO {

    private Integer commentLevel;

    private String content;

    private String specName;

    private Date createdTime;

    private String userFace;

    private String nickname;

}
