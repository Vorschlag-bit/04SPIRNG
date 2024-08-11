package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 설정용 클래스, bean을 인식하게 해주도록 도와주는 클래스이자 이 자체도 곧 콩으로 관리된다.
@Configuration("configurationSection03")
@ComponentScan(basePackages = "com.ohgiraffers.common")
public class ContextConfiguration {
}
