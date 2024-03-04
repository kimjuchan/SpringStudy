package com.example.test.demo;

import com.example.test.demo.department.Department;
import com.example.test.demo.user.domain.Account;
import com.example.test.demo.user.domain.Team;
import com.example.test.demo.user.domain.Usersi;
import com.example.test.demo.user.repository.UserRepository;
import com.example.test.demo.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
class DemoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void entity_의존성관계(){
        Department departmentA = new Department("depA","A부서");
        Department departmentB = new Department("depB","B부서");
        Department departmentC = new Department("depC","C부서");
        Department departmentD = new Department("depD","D부서");
        String deptsList = "ABCD";
        Random random = new Random();


        for(int i=0; i<50; i++){
            int randomValue = (int) (Math.random() * 4) + 1;
            Usersi usersi =
                    Usersi.builder()
                    .memSex((i%2) > 0 ? "F" : "M")
                    //홀수일경우만 결혼g
                    .memMarried((i%2) > 0 ? "Y" : "N")
                    .memPhoneNm("010-1234-123"+i)
                    .memEmail("user"+i+"@naver.com")
                    .build();
            userRepository.save(usersi);
            System.out.println("randomValue :: "  +randomValue );
           /* switch (randomValue){
                case 1 : usersi.setDepartment(departmentA); break;
                case 2 : usersi.setDepartment(departmentB); break;
                case 3 : usersi.setDepartment(departmentC); break;
                case 4 : usersi.setDepartment(departmentD); break;
            }*/

        }
        List<Usersi> usersiList = userRepository.findAll();
        usersiList.forEach(user -> {
            System.out.println("user Dept :: "+ user.getDepartment() + "user email :: " + user.getMemEmail() + "user id :: " + user.getId());
        });

    }

    @BeforeEach
    public void 의존성관계_부모와자식_데이터설정(){
        Team teamA = new Team();
        teamA.setTeamNm("TOT");

        Account account = new Account();
        account.setUserNm("user1");
        account.setTeam(teamA);

        em.persist(account);

    }

    @Test
    public void 의존성테스트AndCascade(){
        System.out.println("#####test#####");

    }


/*
	@Autowired
	private UserService userService;
	@Test
	void getTxServiceTest() {
		System.out.println("----------TX TEST START-----------");
		//userService.createUserListWithTrans();
		System.out.println("----------TX TEST END-----------");

	}
*/

}
