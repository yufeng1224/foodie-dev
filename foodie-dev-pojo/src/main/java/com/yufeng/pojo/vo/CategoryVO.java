package com.yufeng.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 描述:
 *      二级分类VO
 * @author yufeng
 * @create 2020-10-26
 */
@Data
public class CategoryVO {

    private Integer id;

    private String name;

    private String type;

    private String fatherId;

    // 三级分类vo list
    private List<SubCategoryVO> subCatList;


}
