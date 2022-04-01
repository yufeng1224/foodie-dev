package com.yufeng.mapper;

import com.yufeng.pojo.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-10
 */
public interface ItemsCommentsMapperCustom {

    public void saveComments(Map<String, Object> map);

    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}
