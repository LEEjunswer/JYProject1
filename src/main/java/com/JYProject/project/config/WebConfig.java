package com.JYProject.project.config;

import com.JYProject.project.interceptor.AdminInterceptor;
import com.JYProject.project.interceptor.LogInterceptor;
import com.JYProject.project.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    // 프로필 이미지 저장 장소
    @Value("${profile.upload.path}")


    private String profileUploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/" + uploadPath + "/");
        registry.addResourceHandler("/static/profile/**")
                .addResourceLocations("file:/" + profileUploadPath+ "/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Autowired
    private LogInterceptor logInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private  AdminInterceptor adminInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                .order(1); // 모든 경로에 대해 로깅을 수행하는 Interceptor  우선 순위를 정한다.
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/members/join", "/static/**", "/uploads/**", "/profile/**")
                .order(3);
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");
    }
}