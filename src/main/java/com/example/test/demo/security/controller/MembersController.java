package com.example.test.demo.security.controller;


import com.example.test.demo.security.dto.MemberCreateRequest;
import com.example.test.demo.security.service.MembersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.ref.PhantomReference;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class MembersController {

    private final MembersService membersService;

    @PostMapping("/sign")
    public void membersCreate(@Valid MemberCreateRequest memberCreateRequest){
        //MemberCreateRequest 객체 유효성 체크

        int createResult = membersService.membersCreate(memberCreateRequest);


    }




}
