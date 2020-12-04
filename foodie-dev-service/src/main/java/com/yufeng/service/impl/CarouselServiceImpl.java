package com.yufeng.service.impl;

import com.yufeng.mapper.CarouselMapper;
import com.yufeng.pojo.Carousel;
import com.yufeng.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 描述:
 *      轮播图实现
 * @author yufeng
 * @create 2020-10-23
 */
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAll(Integer isShow) {

        Example example = new Example(Carousel.class);
        /** 写入排序  默认是asc() */
        example.orderBy("sort").desc();
        Example.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.andEqualTo("isShow", isShow);

        List<Carousel> carouselList = carouselMapper.selectByExample(example);
        return carouselList;
    }

}
