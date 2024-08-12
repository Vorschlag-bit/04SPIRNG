package com.ohgiraffers.section02.reflection;

import java.lang.reflect.*;

public class Application {
    public static void main(String[] args) {

        // reflection의 특징, 장단점을 정리해서 설명할 수 있게끔 정리해두기
        /* 필기.
        *   리플렉션(reflection)이란?
        *   컴파일 된 자바 코드에서 필드 및 메서드의 정보를 추출하는 방법이다.
        *   spring frame work, 마이바티스, 하이버네이트, jackson 등의 라이브러리에서 사용된다.
        *   스프링에선 런타임 시 개발자가 등록한 bean을 애플리케이션 내부에서 다루기 위한 기술이기도 하다.
        *
        *  필기.
        *   reflection은 강력한 도구이나 무분별하게 사용해선 안 된다.
        *   1. Overhead 발생: 성능 저하를 발생할 수 있기 때문에 민감한 어플리케이션에선 사용 X
        *   2. 캡슐화 저해: private으로 선언된 필드 or method에 쉽게 접근하기 때문에 코드 기능이 저하되며
        *                해석이 어려워 여러가지 문제를 야기할 수 있다.
        * */

        /* 설명. 1. Calss 타입의 Class 메타정보 추출 */
        Class class1 = Account.class;
        System.out.println("class: " + class1);

        Class class2 = new Account().getClass();
        System.out.println("class2 = " + class2);

        try {
            Class class3 = Class.forName("com.ohgiraffers.section02.reflection.Account");
            System.out.println("class3 = " + class3);

            Class class4 = Class.forName("[D");
            Class class5 = double[].class;
            System.out.println("class4 = " + class4);
            System.out.println("class5 = " + class5);

            Class class6 = Class.forName("[Ljava.lang.String;");
            Class class7 = String[].class;

            System.out.println("class6 = " + class6);
            System.out.println("class7 = " + class7);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        /* 설명. 2. 필드 정보 추출 */
        Field[] fields = Account.class.getDeclaredFields();
        for(Field field : fields) {
            System.out.println("modifiers: " + Modifier.toString(field.getModifiers())
                    + ", type: " + field.getType()
                    + ", name: " + field.getName());
        }

        /* 설명. 3. 생성자 정보 추출 */
        // 생성자의 파라미터 개수와 타입 반환
        Constructor[] constructors = Account.class.getConstructors();
        for(Constructor constructor : constructors) {
            System.out.println("name: " + constructor.getName());

            Class[] params = constructor.getParameterTypes();
            for(Class param : params) {
                System.out.println("paramType: " + param.getTypeName());
            }
        }
        try {
            Account acc = (Account) constructors[0].newInstance("20", "110-223-123456", "1234", 10000);
            System.out.println(acc.getBalance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        /* 설명. 4. 메서드 정보 추출 */
        Method[] methods = Account.class.getMethods();
        Method getBalanceMethod = null;
        for(Method method : methods) {
            System.out.println(Modifier.toString(method.getModifiers()) + " "
                                + method.getReturnType().getSimpleName() + " "
                                + method.getName());
            if("getBalance".equals(method.getName())) {
                getBalanceMethod = method;
            }
        }
        try {
            // getBalanceMethod를 invoke 아래의 객체가 호출한 셈으로 치고 실행하라는 의미
            // 기본생성자로 생성된 객체(this), invoke라는 말의 의미를 좀 더 알아둘 필요가 있다...
            // reflection은 무엇인지, aop를 전.후처리를 할 수 있냐 없냐...를 다 공부해보자
            System.out.println(getBalanceMethod.invoke((Account)constructors[2].newInstance()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
