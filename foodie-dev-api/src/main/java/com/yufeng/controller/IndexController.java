package com.yufeng.controller;

import com.yufeng.enums.YesOrNo;
import com.yufeng.pojo.Carousel;
import com.yufeng.pojo.Category;
import com.yufeng.pojo.vo.CategoryVO;
import com.yufeng.pojo.vo.NewItemsVO;
import com.yufeng.service.CarouselService;
import com.yufeng.service.CategoryService;
import com.yufeng.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-23
 */

@Api(value = "首页", tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    /**
     * 1. 注意这里使用的是CarouselService, 而不是CarouselServiceImpl
     * 2. 跟我自己创建的项目 pay 不一样!
     * */
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel() {
        // 写死的数据尽量做到通用化!
        List<Carousel> carouselList =  carouselService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(carouselList);
    }


    /**
     * 首页分类展示需求:
     *    1. 第一次刷新主页查询大分类, 渲染展示到首页
     *    2. 如果鼠标移动到大分类, 则加载其子分类的内容, 如果已经存在子分类, 则不需要加载(懒加载模式)
     *    3. 只加载一次
     */
    @ApiOperation(value = "获取商品分类(一级分类)", notes = "获取商品分类(一级分类)", httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats() {
        List<Category> categoryList =  categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(categoryList);
    }


    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        List<CategoryVO> categoryVOList =  categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(categoryVOList);
    }


    @ApiOperation(value = "查询每个一级分类下的最新的6条商品数据", notes = "查询每个一级分类下的最新的6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems (
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> list =  categoryService.getSixNewItemLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }

}
