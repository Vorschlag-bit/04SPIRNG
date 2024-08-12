package com.ohgiraffers.section03.properties.subsection01.properties;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
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

        ((AnnotationConfigApplicationContext)context).close();
    }
}
