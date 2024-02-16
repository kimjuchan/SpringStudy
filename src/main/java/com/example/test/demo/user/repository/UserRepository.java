package com.example.test.demo.user.repository;


import com.example.test.demo.user.domain.Usersi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Usersi, Long> {
}
