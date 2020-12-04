package com.yufeng.service.impl;

import com.yufeng.service.StuService;
import com.yufeng.service.TestTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:
 *      事务传播行为掌握
 * @author yufeng
 * @create 2020-10-19
 */
@Service
public class TestTransServiceImpl implements TestTransService {

    @Autowired
    private StuService stuService;

    /**
     * 事务传播 - Propagation
     *      REQUIRED: 使用当前的事务, 如果当前没有事务, 则自己新建一个事务, 子方法是必须运行在一个事务中的;
     *                如果当前存在事务, 则加入这个事务, 成为一个整体;
     *                (1. 无论是父方法还是子方法，只要有一个有异常, 数据不会入库，全部回滚)
     *                举例：领导没饭吃，我有钱，我会自己买了自己吃；领导有的吃，会分给你一起吃。
     *
     *      SUPPORTS: 如果当前有事务, 则使用事务; 如果当前没有事务, 则不使用事务。(主要用于做查询)
     *                举例：领导没饭吃，我也没饭吃；领导有饭吃，我也有饭吃。
     *
     *      MANDATORY: 该传播属性强制必须存在一个事务, 如果不存在, 则抛出异常。
     *                 举例: 领导必须管饭，不管饭没饭吃，我就不乐意了，就不干了（抛出异常）
     *
     *      REQUIRES_NEW: 如果当前有事务, 则挂起该事务, 并且自己创建一个新的事务给自己使用;
     *                    如果当前没有事务, 则同 REQUIRED。
     *                    (1. 子事务异常, 挂起的父事务如果没有try-catch, 也会受到异常进行回滚)
     *                    (2. 父事务异常, 子事务没有异常。 子事务数据会入库, 父事务数据回滚，不会入库)
     *                    举例：领导有饭吃，我偏不要，我自己买了自己吃
     *
     *      NOT_SUPPORTED: 如果当前有事务, 则把事务挂起, 自己不使用事务去运行数据库操作。
     *                     (如果父方法有事务, 则父方法事务中的数据会回滚!)
     *                     举例：领导有饭吃，分一点给你，我太忙了，放一边，我不吃
     *
     *      NEVER: 如果当前有事务存在, 则抛出异常。
     *             举例: 领导有饭给你吃，我不想吃，我热爱工作，我抛出异常
     *
     *      NESTED: 如果当前有事务，则开启子事务（嵌套事务），嵌套事务是独立提交或者回滚；
     *              如果当前没有事务，则同 REQUIRED。
     *              但是如果主事务提交，则会携带子事务一起提交。
     *              如果主事务回滚，则子事务会一起回滚。相反，子事务异常，则父事务try-catch之后可以不回滚, 否则的话会回滚。
     *              举例：领导决策不对，老板怪罪，领导带着小弟一同受罪。小弟出了差错，领导可以推卸责任。
     *
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationTrans() {
        stuService.saveParent();

//        try {
//            // save point
        stuService.saveChildren();

//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // delete
        // update


        //int a = 1/0;

    }
}
