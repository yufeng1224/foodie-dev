package com.yufeng.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *    1. 通过日志监控service执行时间， 借助 spring AOP 技术(面向切面功能);
 *    2. 日志知识点掌握(重点!!!)  平时使用的最多的日志框架就是log4j;
 *
 * @author yufeng
 * @create 2020-10-23
 */
@Aspect
@Slf4j
@Component
public class ServiceLogAspect {


    /**
     * AOP通知:
     *    1. 前置通知: 在方法调用之前执行
     *    2. 后置通知: 在方法正常调用之后执行(程序异常了是执行不了的!)
     *    3. 环绕通知: 在方法调用之前和之后, 都分别可以执行的通知
     *    4. 异常通知: 如果在方法调用过程中发生异常, 则通知
     *    5. 最终通知: 在方法调用之后执行
     *
     *    当前项目使用的是环绕通知
     */

    /**
     * 如果是在一个非常庞大的集群, 分布式系统、微服务系统, 可能我们会有上百台、上千台
     * 节点。这个时候日志会分散到不同的节点, 这种使用方式后期可能会不太好。
     * 所以后续会使用到日志收集、监控系统, 使用到kafka。
     */

    /**
     * 切面表达式:
     *    1. execution 代表所要执行的表达式主题;
     *    2. * 代表方法返回类型 *代表所有类型
     *    3. 包名代表aop监控的类所在的包
     *    4. ..代表该包以及其子包下的所有类方法
     *    5. * 代表类名, *代表所有类
     *    6. *(..) *代表类中的方法名, (..)表示方法中的任何参数
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.yufeng.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        /**
         *  {} 表示占位符, 点表示某个类的某个方法
         *  joinPoint.getTarget().getClass(): 类名
         *  joinPoint.getSignature().getName(): 方法名
         *
         */
        log.info("====== 开始执行 {}.{}======" , joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());

        // 记录开始时间
        long begin = System.currentTimeMillis();

        // 执行目标service
        Object result = joinPoint.proceed();

        // 记录结束时间
        long end = System.currentTimeMillis();

        // 根据耗时输出不同的日志级别
        long takeTime = end - begin;
        if (takeTime > 3000) {
            log.error("====== 开始结束，耗时: {} 毫秒 ======", takeTime);
        } else if (takeTime > 2000) {
            log.warn("====== 开始结束，耗时: {} 毫秒 ======", takeTime);
        } else {
            log.info("====== 开始结束，耗时: {} 毫秒 ======", takeTime);
        }

        return result;

    }




}
