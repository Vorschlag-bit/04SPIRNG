package com.ohgiraffers.section01.scope.singleton;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    /* 설명.
    *  bean scope란?
    *   스프링 bean이 인스턴스를 생성하는 시점 및 유지 기간을 설정할 수 있는 Scope 개념이다.
    *   1. singleton: 하나의 인스턴스만을 생성하며 해당 인스턴스가 공유된다.
    *   2. prototype: 매번 bean이 필요로 할 때 새로 인스턴스를 생성한다.
    *   3. request: http 요청을 처리할 때마다 새로 인스턴스를 생성하며 웹 어플리케이션 컨텍스트에만 해당된다.
    *   4. session: http session 당 하나의 인스턴스를 새로 생성하고 세션이 종료되면 인스턴스를 파기한다.
    *               웹 애플리케이션 컨텍스트에만 해당된다.
    *  5. globalSession: 전역 세션 당 하나의 인스턴스를 생성하고 전역 세션이 종료되면 인스턴스를 파기한다.
    *                    포털 애플리케이션 컨텍스트에만 해당된다.
    *  */
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }

        /* 설명. 붕어빵, 바나나우유, 삼다수 진열 */
        Product cartBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /* 설명. 첫번째 손님이 쇼핑카트를 꺼내 물건을 담는다. */
        ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
        cart1.addItem(cartBread);
        cart1.addItem(milk);

        /* 설명. 첫번째 손님의 쇼핑 카트에 담긴 물품 확인 */
        System.out.println("car1에 담긴 물푼: " + cart1.getItems());

        /* 설명. 두번째 손님도 쇼핑 카트에 물건을 담는다. */
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        // 하나의 카트를 싱글톤으로 사용한다.(쇼핑 카트가 하나인 셈, 빈은 하나의 객체만 쓰는 싱글톤이므로)
        System.out.println("car2에 담긴 물품: " + cart2.getItems());
        System.out.println(System.identityHashCode(cart1) == System.identityHashCode(cart2));
    }
}
