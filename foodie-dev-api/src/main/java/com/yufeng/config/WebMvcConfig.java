package com.yufeng.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-02
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * (为静态资源提供网络映射服务)
     * 实现静态资源的映射
     * 举例: localhost:8088/foodie/faces/xxxx.png
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")                               // 映射所有
        .addResourceLocations("classpath:/META-INF/resources/")                        // 映射 swagger2
        .addResourceLocations("file:/workspaces/images/");                             // 映射本地静态资源(这样就可以在浏览器中打开)
    }
}
