package com.example.test.demo.aopProxy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AopProxyController {
    @GetMapping("/aop/test")
    public void aopPorxyTest(){
        log.info("AOP PROXY CONTROLLER");
    }
}
