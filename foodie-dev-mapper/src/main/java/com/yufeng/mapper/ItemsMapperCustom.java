package com.yufeng.mapper;

import com.yufeng.pojo.vo.ItemCommentVO;
import com.yufeng.pojo.vo.SearchItemsVO;
import com.yufeng.pojo.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-27
 */
public interface ItemsMapperCustom {


    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);


    public List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);


    public List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);


    public List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);


    public int decreaseItemSpecStock(@Param("specId") String specId, @Param("pendingCounts") int pendingCounts);

}
