package com.zerobase.convpay.config;

import com.zerobase.convpay.ConvpayApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스가 스프링의 설정 클래스임을 나타냄
@ComponentScan(basePackageClasses = ConvpayApplication.class)
public class ApplicationConfig {
}
