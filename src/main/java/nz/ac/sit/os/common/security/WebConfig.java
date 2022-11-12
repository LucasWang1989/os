package nz.ac.sit.os.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: os
 * @description: Security configuration
 * @author: wangliang (Lucas Wang)
 * @email: lucas.wang.1024@gmail.com
 * @date: 2022-10-30 13:23
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/css/**")
                .excludePathPatterns("/admin/iconsv")
                .excludePathPatterns("/admin/images/**")
                .excludePathPatterns("/admin/js/**")
                .excludePathPatterns("/admin/vendor/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/login.jsp");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}