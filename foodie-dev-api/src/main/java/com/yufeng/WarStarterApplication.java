package com.yufeng;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-12
 */
// 打包war[4] 添加war的启动类
public class WarStarterApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向Application这个springboot启动类
        return builder.sources(Application.class);
    }
}
