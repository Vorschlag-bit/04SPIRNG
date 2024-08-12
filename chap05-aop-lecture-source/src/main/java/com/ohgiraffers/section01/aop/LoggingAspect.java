package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect  // 기능과 시점을 정하는 클래스다
@Component
public class LoggingAspect {

    /* 설명.
    *  타켓 클래스의 메서드에서 advice를 적용할 수 있는 지점들을 joinPoint라고 한다.
    *  포인트컷(pointcut)은 여러 조인포인트들에 advice를 적용할 곳을 지정한 것이다.
    *  해당 조인포인트에서 advice가 동작한다.
    *
    *  설명.
    *   <포인트컷 표현식>
    *   execution([수식어] 리턴타입 [클래스이름], 이름(파라미터))
    *  1. 수식어: public, private 등 수식어를 명시(생략가능)
    *  2. 리턴 타입: 리턴 타입을 명시
    *  3. 클래스 이름(패키지명 포함) 및 메서드 이름: 클래스 이름과 메서드 이름을 명시
    *  4. 파라미터(매개변수0; 메서드의 파라미터를 명시
    *  5. "*": 1개이면서 모든 값이 몰 수 있음
    *  6. "..": 0개 이상의 모든 값이 올 수 있음
    *
    *  설명.
    *   execution(public Integer.com.ohgiraffer.section.advice*.*(*))
    *   => com.ohgiraffers.section01.advice 패키지에 속해 있는 바로 다음 하위 클래스에 파라미터가 1개인 모든
    *   메서드이자 접근 제어자가 public이고 반환형이 Integer인 경우
    *   execution(* com.ohgiraffers.section01.advice.annotation..stu*(..))
    *   => com.ohgiraffers.section01.advice 패키지 및 하위 패키지에 속해 있고 이름이 stu로 시작하는 파라미터가
    *   0개 이상인 모든 메서드이며 접근제어자와 반환형은 상관없음
    * */


    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")  // 우리가 구체적으로 적용하기로 결정한 지점
    public void logPointCut(){}

    /* 설명. 1. Before Advice */
//    @Before("execution(* com.ohgiraffers.section01.aop.*Service.*(..))") // ..은 매개변수가 없어도 있어도 된다는 의미(정규 표현식)
    // 반환형도 상관없고 메서드도 Service만 붙어 있으면 되고 매개변수도 없어도 그만인 계층에 다 적용할 거란 의미
    @Before("LoggingAspect.logPointCut()")  // 너무 aspect가 길어지니 따로 뽑아내는 방법
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before joinPoint.getTarget(): " + joinPoint.getTarget());
        System.out.println("Before joinPoint.getSignature(): " + joinPoint.getSignature());
        if(joinPoint.getArgs().length > 0) {  // 우리의 타겟이 되는 매개변수가 하나라도 있다면!
            System.out.println("Before joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);  // 타켓의 첫번째 요소를 꺼내라
        }
    }

    /* 설명. 2. After Advice */
    @After("LoggingAspect.logPointCut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After joinPoint.getTarget(): " + joinPoint.getTarget());
        System.out.println("After joinPoint.getSignature(): " + joinPoint.getSignature());
        if(joinPoint.getArgs().length > 0) {
            System.out.println("After joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);
        }
    }

    /* 설명. 3. AfterReturning Advice */
    // after의 결과물을 가로챌 수 있는 것, 타겟 메서드의 리턴값이 우리가 원하는 바로 넘어 왔다면 마치 존재하지 않은 3번째 회원을 덧씌워 main으로
    // 리턴하겠다는 의미(후처리 가공)
    @AfterReturning(pointcut = "logPointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning result: " + result);

        if(result != null && result instanceof List) {
            ((List<MemberDTO>) result).add(new MemberDTO(3L, "반환 값 가공"));
        }
    }

    /* 설명. 4. AfterThrowing Advice */
    // 후처리 과정에서 예외가 나온다면 그에 대한 message를 남길 수도 있다.
    @AfterThrowing(pointcut = "logPointCut()", throwing = "exception")
    public void logAfterThrowing(Throwable exception) {
        System.out.println("After Throwing exception: " + exception);
    }

    /* 설명. 5. Around Advice */
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around Before: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();   // target method 출력
        System.out.println("Around After: " + joinPoint.getSignature().getName());

        return result;
    }
}
