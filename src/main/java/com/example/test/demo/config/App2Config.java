package com.example.test.demo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class App2Config {
    private static final Logger log = LogManager.getLogger();
    @Bean
    public String appConfigInit2(){
        log.info("App config init 2 start-------");
        return "appConfigEnd";
    }

}
