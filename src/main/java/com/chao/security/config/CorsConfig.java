package com.chao.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域的路径
        registry.addMapping("/**")
                // 允许跨域的域名
                .allowedOriginPatterns("*")
                // 允许跨域的方式
                .allowedMethods("GET","POST","PUT","DELETE")
                // 允许跨域的 header 属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}
