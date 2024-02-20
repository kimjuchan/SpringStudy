package com.example.test.demo.mapstructTest.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    //차량 이름(entity : carNm mapping)
    private String car_name;
    //차량 제조사(entity : make mapping)
    private String car_make;
    //차량 번호 (entity : carNumber mapping)
    private int car_number;
    //좌석 수 (entity : carSeat mapping)
    private int carSeat;
}