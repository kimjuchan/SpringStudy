package com.example.test.demo.aopProxy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * logging 관련 AOP 구성 테스트
 * proxy 객체를 생성하여, 해당 기능 처리  (트랜잭션도 동일함)
 * @EnableAspectJAutoProxy default = false -> CGLIB 방식으로 PROXY 구현 , true -> JDK Dynamic 방식으로 구현.  ->
 * 자동으로 spring boot내에서 런타임 시점에 AOP에 대한 프록시 객체 생성 후 빈으로 등록하여, 선언 따로 해줄 필요 없음.
 * Controller 단계 전후에 @Custom Annotaion or Pakage 경로작성하여, 사용.
 * 참조  :https://congsong.tistory.com/25
 * (상세 사용법에 대해서 자세히 나옴)
 */
@Aspect
@Component
@Slf4j
public class LogginAspectTest {
    /**
     * Before: 대상 “메서드”가 실행되기 전에 Advice를 실행합니다.
     *
     * @param joinPoint
     */
    //@Before("execution(* com.example.test.demo.user.controller*.*(..))")
    //@Before("within(* com.example.test.demo.user.controller*.*(..))")
    @Before("@annotation(com.example.test.demo.aopProxy.annotation.LoggingAnnotations)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before: " + joinPoint.getSignature().getName());
        log.info("AOP START Before UserService----- : ");
    }

    /**
     * After : 대상 “메서드”가 실행된 후에 Advice를 실행합니다.
     *
     * @param joinPoint
     */
    //@After("execution(* com.example.test.demo.user.controller.*.*(..))")
    @After("@annotation(com.example.test.demo.aopProxy.annotation.LoggingAnnotations)")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After: " + joinPoint.getSignature().getName());
    }

    /**
     * AfterReturning: 대상 “메서드”가 정상적으로 실행되고 반환된 후에 Advice를 실행합니다.
     *
     * @param joinPoint
     * @param result
     */
    //@AfterReturning(pointcut = "execution(* com.example.test.demo.user.controller.*.*(..))", returning = "result")
    @AfterReturning(pointcut = "@annotation(com.example.test.demo.aopProxy.annotation.LoggingAnnotations)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("AfterReturning: " + joinPoint.getSignature().getName() + " result: " + result);
    }

    /**
     * AfterThrowing: 대상 “메서드에서 예외가 발생”했을 때 Advice를 실행합니다.
     *
     * @param joinPoint
     * @param e
     */
    //@AfterThrowing(pointcut = "execution(* com.example.test.demo.user.controller.*.*(..))", throwing = "e")
    @AfterThrowing(pointcut = "@annotation(com.example.test.demo.aopProxy.annotation.LoggingAnnotations)", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.info("AfterThrowing: " + joinPoint.getSignature().getName() + " exception: " + e.getMessage());
    }

    /**
     * Around : 대상 “메서드” 실행 전, 후 또는 예외 발생 시에 Advice를 실행합니다.
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    //@Around("execution(* com.example.test.demo.user.controller.*.*(..))")
    @Around("@annotation(com.example.test.demo.aopProxy.annotation.LoggingAnnotations)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Around before: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info("Around after: " + joinPoint.getSignature().getName());
        return result;
    }











}
