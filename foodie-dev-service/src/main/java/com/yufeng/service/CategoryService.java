package com.yufeng.service;

import com.yufeng.pojo.Category;
import com.yufeng.pojo.vo.CategoryVO;
import com.yufeng.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-26
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    public List<Category> queryAllRootLevelCat();


    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);


    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemLazy(Integer rootCatId);

}
