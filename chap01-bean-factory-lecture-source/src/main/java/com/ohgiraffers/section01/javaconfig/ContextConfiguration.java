package com.ohgiraffers.section01.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {
    @Bean(name="member")
    public MemberDTO getMemberDTO() {
        return new MemberDTO(1, "user01", "pass01", "홍길동");
    }
}
