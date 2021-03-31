package com.softuni.config;

import com.softuni.web.interceptor.PageTitleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final PageTitleInterceptor pageTitleInterceptor;

    public WebConfiguration(PageTitleInterceptor pageTitleInterceptor) {
        this.pageTitleInterceptor = pageTitleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageTitleInterceptor);
    }
}
