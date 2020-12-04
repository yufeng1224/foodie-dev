package com.yufeng.mapper;

import com.yufeng.pojo.vo.CategoryVO;
import com.yufeng.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *      自定义mapper，其实我更喜欢用 CategoryMapperExt的形式, 表示扩展
 *
 *      (多表关联查询和自连接查询是用不到通用mapper的, 所以需要写自定义mapper!)
 * @author yufeng
 * @create 2020-10-26
 */
public interface CategoryMapperCustom {

    public List<CategoryVO> getSubCatList(Integer rootCatId);


    public List<NewItemsVO> getSixNewItemLazy(@Param("paramsMap") Map<String, Object> map);

}
