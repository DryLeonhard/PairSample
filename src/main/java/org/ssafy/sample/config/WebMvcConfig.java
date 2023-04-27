package org.ssafy.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.ssafy.sample.util.SampleInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final SampleInterceptor sampleInterceptor;

    public WebMvcConfig(SampleInterceptor sampleInterceptor) {
        this.sampleInterceptor = sampleInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sampleInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/images/**","/js/**","/index*","/api/login*","/","/api/members/register");
    }
}
