package com.example.test.demo.user.controller;


import com.example.test.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class UserRestController {

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;
    //user 전체 목록 조회.
    //param 값 없는 기준으로 전체 조회 테스트 진행.
    @GetMapping("/users")
    public String getUsers(){
        userService.createUserListWithTrans();
        return "users return";
    }

}
