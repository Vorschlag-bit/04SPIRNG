package com.ohgiraffers.section01.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        // Configuration이라는 어노테이션이 붙어있는 콩을 담을 컨테이너
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames(); // 콩 이름 다 추출
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        /* 설명. bean의 id(bean의 이름)을 이용해서 bean을 가져오는 방법 */
//        MemberDTO member = (MemberDTO) context.getBean("member");  콩 이름 말하며 이 콩 주세요

        /* 설명. bean의 클래스 메타 정보(bean의 타입)을 전달하여 가져오는 방법 */
//        MemberDTO member = context.getBean(MemberDTO.class);  // 콩을 보여주면서 이 콩 주세요

        /* 설명. bean의 id와 타입을 명시하여 하나의 콩만을 명시적으로 가져오는 방법 */
        MemberDTO member = context.getBean("member", MemberDTO.class);

        System.out.println("member = " + member);
    }
}
