package com.example.test.demo.user.service;



import com.example.test.demo.aopProxy.annotation.LoggingAnnotations;
import com.example.test.demo.user.domain.Usersi;
import com.example.test.demo.user.domain.Usersi2;
import com.example.test.demo.user.repository.User2Repository;
import com.example.test.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    /**
     * @Autowired 의존성 주입할경우에도 proxy pattern 기반
     */
    //외부 class로 내부 메소드 Tx
    private final UserTxService userTxService;

    private final UserRepository userRepository;

    private final User2Repository user2Repository;


    //getUsers

    //detailUser

    //update

    //delete

    //save

    /**
     * @Target : Transactional
     * 참조 : https://chikeem90.tistory.com/139
     * CASE 1) 상위 메소드에 TX 미선언 AND 하위 메소드에 TX 선언되었을 경우.   -> Rollback 처리 불가능. createUser 정상적으로 10개의 유저 정보 등록.
     * CASE 2) 상위 메소드 TX , 하위 메소드 TX  모두 전파레벨 default = "REQUIRED" 일경우  상위 메소드 TX만 적용. -> RuntimeException (chked Exception) 발생 시 롤백 처리.
     * CASE 3) 상위 메소드 TX , 하위 메소드 TX(전파 레벨 : REQUIRES_NEW 새 TX 만들경우) 안먹힘...-> 전체 롤백   (물리(논리 TX 1)), (물리(논리 TX 2)) 생성될 줄 알았는데...
     * -> CASE 3 번 같은 경우에는 예외처리를 상위 메소드에 지정해줘야 예상 되었던 결과 값이 나옴. (하위에서 예외처리만 적용해서 하위만 롤백하는 경우는 불가능한건가 ??)
     * 기본적인 우선순위 : 클래스 내부 메소드 > 클래스 > 인터페이스 내부 메소드 > 인터페이스

     * --> CASE 3번에 대해서 잘못 알고 있었던 부분.
     * 만약 같은 클래스내에 상위 메소드 하위 메소드 @TX 관련해서는 아무리 하위에 전파레벨을 REQUIRES_NEW로 선언하더라도 상위에 따라가게되있음.
     * 만약에 새로운 TX을 하위 메소드에 걸어줘서 하위에 대해서만 롤백처리를 하고 싶다면 하위 메소드 자체를 새로운 외부 클래스로 선언하여 REQUIRES_NEW를 선언해줘야함.
     * >>그냥 상위 시작 메소드에서 TX가 안걸려있으면 무조건 하위메소드는 PROXY 객체가 아닌 this.method() 객체 자기 자신꺼 호출해옴   이게 포인트임.
     *
     */

    /**
     * @Target : Transactional Exception
     * 추가적으로 예외처리 관한 내용 정리.
     *              Throwable
     *      | ----------------------|
     * Exception(checked)     Error(unchecked) *OutOfMemoryError, StackOverflowError
     *      |
     *  RuntimeException(unchecked)
     *  >>예외처리 발생 시점
     *  checked : 컴파일 시점,  unchecked : 런타임 시점 (NullpointerException , IllegalArgumentException)
     * @TX 기본적으로 Unchecked Exception에 대해서 default로 예외처리 진행 (추가 설정으로 변경 가능)  @Transactional(rollbackFor = DataFormatException.class) 특정 checked Exception에 대해서 롤백 여부 추가로 작성해주면 가능.
     * TransactionAspectSupport.class 참조 : https://sup2is.github.io/2021/03/04/java-exceptions-and-spring-transactional.html
     * --격리 레벨
     * https://dar0m.tistory.com/225
     * Read Uncommitted (0) : 트랜잭션 처리 중 or 아직 Commit 되지 않은 데이터에  다른 트랜잭션이 읽는 것 허용.
     * Read Committed (1) : SELECT 수행 동안 share lock을 통해서 읽는 동안 다른 트랜잭션이 접근 불가. 대부분의 SQL 서버 Default로 사용 레벨
     * Repeatable Read (2) : 트랜잭션 수행 완료까지 데이터에 Share lock 걸림.
     * Serializable (3)
     */

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


    @Transactional
    @LoggingAnnotations
    public void createUserListWithTrans(){
        System.out.println("==== UserService.createUserListWithTrans TX Active : " + TransactionSynchronizationManager.isActualTransactionActive());
        //log.info("==== UserService.createUserListWithTrans TX Active : {}", TransactionSynchronizationManager.isActualTransactionActive());
        for (int index = 0; index < 10; index++) {
            //첫번째 상위 메소드내 USER2 정보 INSERT
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

            //하위 메소드에 새로운 트잭 생성 선언 후 USER1 정보 INSERT
            //userTxService.createUser1(index);
            //userTxService.createUser2(index);
            createUser1(index);
        }
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createUser1(int index){
        System.out.println("==== innerMethod transaction Active : " + TransactionSynchronizationManager.isActualTransactionActive());
        //log.info("==== innerMethod transaction Active : {}", TransactionSynchronizationManager.isActualTransactionActive());
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
