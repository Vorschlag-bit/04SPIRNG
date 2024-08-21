package com.ohgiraffers.section02.annotation.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component

/* 설명. Pokemon 타입 중 CHarmander bean에 대한 우선권을 높이는 annotation */
@Primary
public class Charmander implements Pokemon{
    @Override
    public void attack() {
        System.out.println("파이리의 불대문자!!");
    }
}
