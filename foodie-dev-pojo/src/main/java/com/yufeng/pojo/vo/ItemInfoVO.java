package com.yufeng.pojo.vo;

import com.yufeng.pojo.Items;
import com.yufeng.pojo.ItemsImg;
import com.yufeng.pojo.ItemsParam;
import com.yufeng.pojo.ItemsSpec;
import lombok.Data;

import java.util.List;

/**
 * 描述:
 *      商品详情VO
 * @author yufeng
 * @create 2020-10-26
 */
@Data
public class ItemInfoVO {

    private Items item;

    private ItemsParam itemParams;

    private List<ItemsImg> itemImgList;

    private List<ItemsSpec> itemSpecList;

}
