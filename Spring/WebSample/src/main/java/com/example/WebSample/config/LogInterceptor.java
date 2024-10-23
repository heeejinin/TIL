package com.example.WebSample.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


// LogInterceptor는 컴포넌트로 바로 등록하지 않고,
// WebConfig에서 WebMvcConfigurer로 등록을 해줌
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandle LogInterceptor : " + Thread.currentThread());
        log.info("preHandle handler : " + handler);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle LogInterceptor : " + Thread.currentThread());
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        log.info("afterHandle LogInterceptor : " + Thread.currentThread());

        if (ex != null) {
            log.error("afterCompletion exception : " + ex.getMessage());
        }
    }
}

