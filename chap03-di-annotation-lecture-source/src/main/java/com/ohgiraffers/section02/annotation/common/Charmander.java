package com.ohgiraffers.section02.annotation.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Charmander implements Pokemon{
    @Override
    public void attack() {
        System.out.println("파이리의 불대문자!!");
    }
}
