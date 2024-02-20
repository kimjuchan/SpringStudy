package com.example.test.demo.aopProxy.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggingAnnotations {
    //Logging Aspect 실행을 위한 커스텀 어노테이션 작성.
}
