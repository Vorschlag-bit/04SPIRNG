package com.ohgiraffers.common;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/* 설명.
*  DAO class는 Data Access Object를 줄인 말로 Repository 계층과 java application과
*  Database를 연동시켜 주기 위한 계층을 구분하느 클래스로 활용된다.(Controller - service - dao)
*  */

/* 설명.
*  @Repository란?
*   1. @Component 계열로 스프링 컨테이너(IOC Container)가 bean으로 등록하는 클래스에 추가하는
*   annotation이다.
*   2. DAO(OR Repository) 계층에 MVC 구조에 맞춰 구분하기 위해 추가하는 annotation이다.
*   (추가적으로 DB에서 발생한 에러를 자바의 예외 타입으로 바꿔주는 부가 기능이 있다)
* */
@Repository
//@Component @Component라고 해도 bean으로 관리될 수 있다! 다만 이렇게하면 layer system을 전혀 신경쓰지 않겠다는 의미
public class MemberDAO {

    private final Map<Integer, MemberDTO> memberMap;

    public MemberDAO() {
        memberMap = new HashMap<>();

        memberMap.put(1, new MemberDTO(1, "user01", "pass01", "홍길동"));
        memberMap.put(1, new MemberDTO(2, "user02", "pass02", "유관순"));
        memberMap.put(1, new MemberDTO(3, "user03", "pass03", "이순신"));
    }

    /* 설명. 회원 조회용 메서드 */
    public MemberDTO selectMember(int sequence) {
        return memberMap.get(sequence);
    }

    /* 설명. 회원 가입용 메서드 */
    public int insertMember(MemberDTO registmember) {
        int before = memberMap.size();
        memberMap.put(registmember.getSequence(), registmember);

        int after = memberMap.size();

        return after - before;
    }


}
