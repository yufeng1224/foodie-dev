package com.yufeng.service.impl.center;

import com.github.pagehelper.PageInfo;
import com.yufeng.utils.PagedGridResult;

import java.util.List;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-10
 */
public class BaseService {


    public PagedGridResult setterPageGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult gridResult = new PagedGridResult();
        gridResult.setRows(list);
        gridResult.setPage(page);
        gridResult.setTotal(pageList.getPages());
        gridResult.setRecords(pageList.getTotal());
        return gridResult;
    }

}
