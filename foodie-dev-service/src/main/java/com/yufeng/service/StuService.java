package com.yufeng.service;

import com.yufeng.pojo.Stu;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-09
 */
public interface StuService {

    public Stu getStuInfo(int id);

    public void saveStu();

    public void updateStu(int id);

    public void deleteStu(int id);

    public void saveParent();

    public void saveChildren();

}
