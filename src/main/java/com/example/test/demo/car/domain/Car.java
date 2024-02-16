package com.example.test.demo.car.domain;


import com.example.test.demo.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




//Map Struct test용 Entity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @Column
    //차량 이름
    private String carNm;
    @Column
    //제조사
    private String make;
    @Column
    //차량 번호
    private int carNumber;
    @Column
    //좌석 수
    private int numberOfSeats;
}
