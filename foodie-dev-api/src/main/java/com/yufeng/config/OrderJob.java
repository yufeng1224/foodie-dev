package com.yufeng.config;

import com.yufeng.service.OrderService;
import com.yufeng.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *      订单查询关闭定时任务
 * @author yufeng
 * @create 2020-12-04
 */

@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    /**
     * 使用定时任务关闭超期未支付订单, 会存在的弊端:
     *    1. 会有时间差。 程序不严谨
     *       比如: 10:39下单, 11:00检查，不足一小时. 12:00检查, 超过1小时多于39分钟。
     *
     *    2. 定时任务是不支持集群的。
     *       单机没毛病, 使用集群后, 就会有多个定时任务.
     *       解决方案: 只使用一台计算机节点, 单独用来执行所有的定时任务
     *
     *    3. 会对数据库全表搜索, 极其影响数据库性能
     *       (假设数据量十万、百万条, 那么这么操作肯定不行)
     *       select * from order where orderStatus = 10;
     *
     *    定时任务, 仅仅只适用于小型轻量级项目, 传统项目。 (但是后期也是需要考虑量变的!)
     *
     * 后续课程会涉及到消息队列: MQ --> RabbitMQ, RocketMQ, kafka, ZeroMQ。
     *    使用延时任务(队列)
     *    10:12分下单的, 未付款(10)状态, 11:12分检查。 如果当前状态还是10, 则直接关闭订单即可。
     *
     */

    // 每隔一个小时执行
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoCloseOrder() {

        orderService.closeOrder();

        System.out.println("执行定时任务, 当前时间为: "
                + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));

    }



}
