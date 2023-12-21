package com.netheve.andikagunshop.common.configuration;

import com.netheve.andikagunshop.common.configuration.interceptor.RequiredRankInterceptor;
import com.netheve.andikagunshop.entity.enums.AccountRank;
import com.netheve.andikagunshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequireRankConfiguration implements WebMvcConfigurer {
    @Autowired
    private AuthService authService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequiredRankInterceptor(authService, AccountRank.ADMIN))
                .excludePathPatterns("/**")
                .addPathPatterns("/order/approve")
                .addPathPatterns("/order/cancel");
    }
}
