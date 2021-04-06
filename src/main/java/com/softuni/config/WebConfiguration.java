package com.softuni.config;

import com.softuni.web.interceptor.FaviconInterceptor;
import com.softuni.web.interceptor.PageTitleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final PageTitleInterceptor pageTitleInterceptor;
    private final FaviconInterceptor faviconInterceptor;

    public WebConfiguration(PageTitleInterceptor pageTitleInterceptor, FaviconInterceptor faviconInterceptor) {
        this.pageTitleInterceptor = pageTitleInterceptor;
        this.faviconInterceptor = faviconInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.faviconInterceptor);
        registry.addInterceptor(this.pageTitleInterceptor);
    }
}
