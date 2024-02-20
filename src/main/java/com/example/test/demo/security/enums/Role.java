package com.example.test.demo.security.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("관리자"),
    USER("사용자");

    private String description;

}
