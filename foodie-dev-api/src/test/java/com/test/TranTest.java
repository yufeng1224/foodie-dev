package com.test;

import com.yufeng.Application;
import com.yufeng.service.StuService;
import com.yufeng.service.TestTransService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述:
 *      事务测试类
 * @author yufeng
 * @create 2020-10-16
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class TranTest {

    @Autowired
    private StuService stuService;

    @Autowired
    private TestTransService testTransService;

//    @Test
    public void myTest() {
        //stuService.testPropagationTrans();
        testTransService.testPropagationTrans();
    }

}
