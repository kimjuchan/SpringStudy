package com.example.test.demo.user.service;

import com.example.test.demo.fetchJoinEx.domain.Member;
import com.example.test.demo.user.domain.Usersi;
import com.example.test.demo.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    /**
     * @TEST용으로 만들긴 했지만 아직 사용법이 미숙함.
     */
    @Test
    void getTxServiceTest(){
        System.out.println("----------TX TEST START-----------");
        userService.createUserListWithTrans();
        List<Usersi> member = userRepository.findAll();
        member.stream().forEach(m -> System.out.println("member info :: " + m.getMemEmail()));
        System.out.println("----------TX TEST END-----------");
    }



}