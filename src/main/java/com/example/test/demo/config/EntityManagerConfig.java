package com.example.test.demo.config;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;


//EntityManager Bean으로 주입
@Configuration
@RequiredArgsConstructor
public class EntityManagerConfig {
    @PersistenceContext
    private final EntityManager em;
}
