package com.ohgiraffers.section01.autowired.section02.setter;

import com.ohgiraffers.section01.autowired.section02.setter.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");

        BookService bookService = context.getBean("bookServiceSetter", BookService.class);

        /* 설명. 전체 도서 목록 조회 후 출력 확인 */
        bookService.findAllBook().forEach(System.out::println);

        /* 설명. 도서 번호로 검색 후 출력 확인 */
        System.out.println(bookService.searchBookSeqence(1));
        System.out.println(bookService.searchBookSeqence(2));
    }
}
