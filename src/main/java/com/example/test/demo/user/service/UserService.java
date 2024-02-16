package com.example.test.demo.user.service;



import com.example.test.demo.user.domain.Usersi;
import com.example.test.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //외부 class로 내부 메소드 Tx
    private final UserTxService userTxService;

    //getUsers

    //detailUser

    //update

    //delete

    //save

    //참조 : https://chikeem90.tistory.com/139
    //CASE 1) 상위 메소드에 TX 미선언 AND 하위 메소드에 TX 선언되었을 경우.   -> Rollback 처리 불가능. createUser 정상적으로 10개의 유저 정보 등록.
    //CASE 2) 상위 메소드 TX , 하위 메소드 TX  모두 전파레벨 default = "REQUIRED" 일경우  상위 메소드 TX만 적용. -> RuntimeException (chked Exception) 발생 시 롤백 처리.
    //CASE 3) 상위 메소드 TX , 하위 메소드 TX(전파 레벨 : REQUIRES_NEW 새 TX 만들경우) 안먹힘...-> 전체 롤백   (물리(논리 TX 1)), (물리(논리 TX 2)) 생성될 줄 알았는데...
    //-> CASE 3 번 같은 경우에는 예외처리를 상위 메소드에 지정해줘야 예상 되었던 결과 값이 나옴. (하위에서 예외처리만 적용해서 하위만 롤백하는 경우는 불가능한건가 ??)
    @Transactional
    public void createUserListWithTrans(){
        log.info("==== UserService.createUserListWithTrans TX Active : {}", TransactionSynchronizationManager.isActualTransactionActive());
        for (int index = 0; index < 10; index++) {
            createUser1(index);
            userTxService.createUser2(index);
        }
        throw new RuntimeException(); // user 생성 완료 후 Exception 발생
    }

    //@Transactional
    public void createUser1(int index){
        log.info("==== innerMethod transaction Active : {}", TransactionSynchronizationManager.isActualTransactionActive());
        Usersi user = Usersi.builder()
                //F : 홀수 , M : 짝수
                .memSex((index%2) > 0 ? "F" : "M")
                //홀수일경우만 결혼
                .memMarried((index%2) > 0 ? "Y" : "N")
                .memPhoneNm("010-1234-123"+index)
                .memEmail("user"+index+"@naver.com")
                .build();
        userRepository.save(user);
    }



}
