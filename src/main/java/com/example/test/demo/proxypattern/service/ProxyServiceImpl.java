package com.example.test.demo.proxypattern.service;

import org.springframework.stereotype.Service;

@Service
public class ProxyServiceImpl implements proxyService{

    /**
     * @Target : proxy
     * !Proxy : 실제 Target의 기능을 대신 수행하면서, 기능 확장 추가하는 실제 객체.
     * !Proxy Pattern : 실제 Target의 기능을 확장 x , Client가 Target에 접근하는 방식.    (둘의 차이가 있음)
     * 생성 방식 1) JDK Dynamic proxy
     * interface 기준으로 Reflection을 통해 동적으로 proxy 객체 생성
     * Reflection으로 인하여 성능 저하만 기억남.
     * 생성 방식 2) CGLIB
     * (Enhancer)을 바탕으로 상속 방식으로 class 기준으로 메소드 오버라이딩
     * !Runtime Weaving : Runtime시점에 Weaving(Target 객체를 proxied 객체로 적용시키는 과정)이 진행된다.
     * Spring 4.3 & spring boot 1.4부터 default로 CGLIB을 사용하게 되었다.
     * -> 만약 JDK 동적 프록시를 사용하려면 YML파일에   proxy-target-class : false 처리해주면 가능.
     * @Reflection API
     *
     *
     * 동작 원리
     * Client 요청 > AOP 프로시가 인터셉터 체인을 통해 가로챔 Transaction Advisor에게 전달
     * Transaction Advisor 트잭 생성
     * Custom Advisor가 있다면 실행 후 비지니스 로직 호출
     * Transaction Advisor COMMIT OR ROLLBACK등의 트잭 결과 반환.
     * https://coding-business.tistory.com/83
     */






}
