package com.example.test.demo.security.repository;

import com.example.test.demo.security.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Members,Long> {
    Optional<Members> findByUserName(String userName);

    Optional<Members> findByLoginId(String loginId);

}
