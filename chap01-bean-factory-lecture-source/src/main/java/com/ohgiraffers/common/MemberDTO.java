package com.ohgiraffers.common;

import lombok.*;

@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor  // 모든 매개변수 생성자
@Setter
@Getter
@ToString
public class MemberDTO {
    private int sequence;
    private String id;
    private String pwd;
    private String name;
}
