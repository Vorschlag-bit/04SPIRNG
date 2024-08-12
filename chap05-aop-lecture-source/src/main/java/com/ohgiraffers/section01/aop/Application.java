package com.ohgiraffers.section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01.aop");


        MemberService memberService = (MemberService) context.getBean("memberService", MemberService.class);
        System.out.println("======= select all members ========");

        /* 설명. 1. 회원 전체 조회(매개변수 없는 타겟 메서드인 경우) */
        List<MemberDTO>members = memberService.findAllMembers();
        members.forEach(System.out::println);

        /* 설명. 2. 회원 한 명 조회(매개변수가 한 개인 타겟 메서드인 경우) */
        System.out.println("======= select one members ========");
        System.out.println(memberService.findMemberBy(1));

        /* 설명. 3. 3번 인덱스 조회로 예외 발생시켜보기(AfterThrowing Advice 동작) */
//        memberService.findMemberBy(3);

    }
}
