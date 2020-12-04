package com.yufeng.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 描述:
 *      设置跨域配置实现前后端联调
 * @author yufeng
 * @create 2020-10-22
 *
 * 什么是跨域访问（CORS）?
 *     CORS是一个W3C标准，全称是"跨域资源共享"（Cross-origin resource sharing）。
 *     它允许浏览器向跨源服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。
 */
@Configuration
public class CorsConfig {

    public CorsConfig() {}

    @Bean
    public CorsFilter corsFilter() {
        // 1. 添加 cors 配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:8080");            // 设置前端为8080
        // 设置是否发送cookie信息
        configuration.setAllowCredentials(true);
        // 设置允许请求的方式
        configuration.addAllowedMethod("*");
        // 设置允许的header
        configuration.addAllowedHeader("*");

        // 2. 为url添加映射路径
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);

        // 3. 返回重新定义好的configurationSource
        return new CorsFilter(configurationSource);
    }

}
