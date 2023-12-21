package com.netheve.andikagunshop.common.configuration;


import com.netheve.andikagunshop.common.configuration.interceptor.SimpleTokenInterceptor;
import com.netheve.andikagunshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestTokenConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthService authService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleTokenInterceptor(authService))
                //.addPathPatterns("/**")
                .excludePathPatterns("/storage/**")
                .excludePathPatterns("/auth/login")
                .excludePathPatterns("/auth/register");
    }
}
