package com.yufeng.pojo.bo;

import lombok.Data;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-27
 */
@Data
public class ShopcartBO {

    private String itemId;

    private String itemImgUrl;

    private String itemName;

    private String specId;

    private String specName;

    private Integer buyCounts;

    private String priceDiscount;

    private String pricNormal;

}
