package com.example.test.demo.user.service;

import com.example.test.demo.user.domain.Usersi2;
import com.example.test.demo.user.repository.User2Repository;
import com.example.test.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserTxService {

    private final UserRepository userRepository;

    private final User2Repository user2Repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createUser2(int index){
        log.info("==== UserTxService.createUser2 TX Active : {}", TransactionSynchronizationManager.isActualTransactionActive());
        Usersi2 user2 = Usersi2.builder()
                //F : 홀수 , M : 짝수
                .memSex((index%2) > 0 ? "F" : "M")
                //홀수일경우만 결혼g
                .memMarried((index%2) > 0 ? "Y" : "N")
                .memPhoneNm("010-1234-123"+index)
                .memEmail("user"+index+"@naver.com")
                .build();
        user2Repository.save(user2);
    }

}
