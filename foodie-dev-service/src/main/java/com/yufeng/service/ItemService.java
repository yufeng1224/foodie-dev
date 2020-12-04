package com.yufeng.service;

import com.yufeng.pojo.Items;
import com.yufeng.pojo.ItemsImg;
import com.yufeng.pojo.ItemsParam;
import com.yufeng.pojo.ItemsSpec;
import com.yufeng.pojo.vo.CommentLevelCountsVO;
import com.yufeng.pojo.vo.ItemCommentVO;
import com.yufeng.pojo.vo.ShopcartVO;
import com.yufeng.utils.PagedGridResult;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-26
 */
public interface ItemService {

    /**
     * 根据商品ID查询详情
     * (后期要保证商品ID是全局唯一的, 分布分表需要用到, 所以需要String类型)
     * @param id
     * @return
     */
    public Items queryItemById(String id);


    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);


    /**
     * 根据商品id查询商品规格列表
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);


    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);


    /**
     * 根据商品id查询商品的评价等级数量
     * @param itemId
     */
    public CommentLevelCountsVO queryCommentCounts(String itemId);


    /**
     * 根据商品id查询商品的评价(分页)
     * @param itemId
     * @param level
     * @return
     */
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);


    /**
     * 搜索商品列表
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);


    /**
     * 根据分类id搜索商品列表
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);


    /**
     * 根据规格ids查询最新的购物车中商品数据(用于刷新渲染购物车中的商品数据)
     * @param spectIds
     * @return
     */
    public List<ShopcartVO> queryItemsBySpecIds(String spectIds);


    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId
     * @return
     */
    public ItemsSpec queryItemSpecById(String specId);


    /**
     * 根据商品id获得商品图片主图url
     * @param itemId
     * @return
     */
    public String queryItemMainImgById(String itemId);


    /**
     * 减少库存
     * @param specId
     * @param buyCounts
     */
    public void decreaseItemSpecStock(String specId, Integer buyCounts);

}
