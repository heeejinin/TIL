package com.example.WebSample.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@Component -> 바로 컴포넌트 등록을 할 수도 있지만
// WebConfig의 FilterRegistrationBean을 통해 Filter로 등록하는 방법도 있음
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 현재 쓰레드 확인을 위한 출력문
        log.info("Hello LogFilter: " + Thread.currentThread());

        // doFilter: 외부 -> filter (-> 처리(chain) ->) filter -> 외부
        // (-> 처리(chain) ->) 부분이 아래 함수
        chain.doFilter(request, response);
        log.info("Bye LogFilter: " + Thread.currentThread());

    }
}
