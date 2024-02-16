package com.example.test.demo.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class AppConfig {
    private static final Logger log = LogManager.getLogger();

    @Bean
    public String appConfigInit(){
        log.info("App config init start-------");
        return "appConfigEnd";
    }

/*    @Bean
    public FilterRegistrationBean firstFilterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AppFilter());
        return registrationBean;

    }*/


}
