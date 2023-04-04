package com.chatAssistant.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/register",
                        "/**/*.css",
                        "/**/*.js"
                );
    }
}
