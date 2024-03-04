package com.example.test.demo.config;

import com.example.test.demo.user.domain.Account;
import com.example.test.demo.user.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Transactional
public class AppRunnerTest implements ApplicationRunner {

    private final EntityManager em;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("###sample code###");

        Team teamA = new Team();
        teamA.setTeamNm("TOT");

        Team teamB = new Team();
        teamB.setTeamNm("B");

        Account account = new Account();
        account.setUserNm("user1");
        account.setTeam(teamA);

        em.persist(account);

        /*account.addTeam(teamB);*/
        System.out.println("######");
        System.out.println("######");

        //teamA.setTeamNm("update_ManU");
        //em.persist(teamA);1

    }
}
