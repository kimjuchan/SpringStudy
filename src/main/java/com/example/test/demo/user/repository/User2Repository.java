package com.example.test.demo.user.repository;
import com.example.test.demo.user.domain.Usersi2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User2Repository extends JpaRepository<Usersi2, Long> {
}
