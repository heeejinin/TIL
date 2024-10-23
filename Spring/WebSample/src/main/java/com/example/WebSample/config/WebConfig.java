package com.example.WebSample.config;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    // FilterRegistration : 말 그대로 Filter를 등록해주기 위한 Bean
    public FilterRegistrationBean loggingFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());

        // 필터들이 다중으로 있을 때, 1번째로 뜰 수 있도록 지정
        filterRegistrationBean.setOrder(1);

        // 특정 url로 filter를 지정할 수 있음 ex) "/order/*"
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    // WebMvcConfigurer 메소드 中 addInterceptors 메소드 구현
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry: 등록한 내역들을 관리하는 등록부 역할
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                // 보통 이런 정적 파일들은 인터셉터를 탈 일이 없어서 제외해줌
                .excludePathPatterns("/css/*", "/images/*");
    }
}
