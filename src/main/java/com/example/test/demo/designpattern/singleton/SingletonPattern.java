package com.example.test.demo.designpattern.singleton;

import com.fasterxml.jackson.annotation.JsonInclude;


//@Scope("prototype")
public class SingletonPattern {


    /**
     * 전역내 하나의 객체만을 생성하여, 동일한 객체를 사용하는 방식.
     * JVM 메모리 구조 관련해서 간략하게 설명
     *
     *
     */
    private static SingletonPattern singletonPattern;

    //
    public SingletonPattern() {
    }

    public static SingletonPattern getInstance(){
        if(singletonPattern == null){
            return new SingletonPattern();
        }
        return singletonPattern;
    }

}
