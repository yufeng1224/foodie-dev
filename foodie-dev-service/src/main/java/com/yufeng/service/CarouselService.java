package com.yufeng.service;

import com.yufeng.pojo.Carousel;

import java.util.List;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-23
 */
public interface CarouselService {

    /**
     * 查询所有的轮播图列表
     * @param isShow
     */
    public List<Carousel> queryAll(Integer isShow);

}
