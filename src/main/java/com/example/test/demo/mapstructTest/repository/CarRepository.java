package com.example.test.demo.mapstructTest.repository;

import com.example.test.demo.mapstructTest.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
