package com.ohgiraffers.section03.properties.subsection02.i18n;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Application {
    public static void main(String[] args) {

        /* 설명.
        *   i18n 소프트웨어의 국제화(Internationalization, I와 n 사이 18 글자)에서 처리해야 할 작업이 많이 있다.
        *   1. 언어, 지역별 번역
        *   2. OS/Platform
        *   3. 문자열 치환 방법
        *   4. 국제화 UI(문자열 크기 변화, 폰트, 아이콘 등)
        *   5. 쓰기 방향의 차이
        *   6. 숫자, 공백, 화폐, 날짜, 주소, 측정 단위 등
        *   7. 타임존, 썸머타임 등 시간
        *   8. 문자열 정렬 방법
        *
        *  설명.
        *   우리가 다뤄본 예제는 그 중에서 1번에 해당하는 다국어 메시지 처리에 있어 살펴봤다.
        * */
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String error404MessageKR = context.getMessage("error.404", null, Locale.KOREA);
        String error500MessageKR =
                context.getMessage("error.500", new Object[]{"당신", new java.util.Date()}
                        , Locale.KOREA);
        System.out.println("i18n error404MessageKR: " + error404MessageKR);
        System.out.println("i18n error500MessageKR: " + error500MessageKR);

        String error404MessageUS = context.getMessage("error.404", null, Locale.US);
        String error500MessageUS =
                context.getMessage("error.500", new Object[]{"you", new java.util.Date()}
                        , Locale.US);
        System.out.println("i18n error404MessageUS: " + error404MessageUS);
        System.out.println("i18n error500MessageUS: " + error500MessageUS);
    }
}
