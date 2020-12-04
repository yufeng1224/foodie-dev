package com.yufeng.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 描述:
 *      最新商品VO
 * @author yufeng
 * @create 2020-10-26
 */
@Data
public class NewItemsVO {

    private Integer rootCatId;

    private String rootCatName;

    private String slogan;

    private String catImage;

    private String bgColor;

    private List<SimpleItemVO> simpleItemList;


}
