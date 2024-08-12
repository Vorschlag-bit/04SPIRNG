package com.ohgiraffers.section01.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberDAO memberDAO;

    // final로 생성자 주입
    @Autowired
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public List<MemberDTO> findAllMembers() {
        System.out.println("target -> findAllMembers activate");
        return memberDAO.selectAllMembers();
    }

    public MemberDTO findMemberBy(int idx) {
        System.out.println("target -> findMemberBy activate");


        return memberDAO.selectmemberBy(idx);
    }
}
