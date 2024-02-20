/*

package com.example.test.demo.car.mapper;


import com.example.test.demo.car.domain.Car;
import com.example.test.demo.car.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//componentModel : "spring" 설정을 통해서 Bean 등록
@Mapper(componentModel = "spring")
public interface CarMapper {

    //Mapper 설정.
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    //Entity  to DTO 변환
    //Source : 원본 대상 , target : 대상 필드
    @Mapping(source = "carNm", target = "car_name")
    @Mapping(source = "make", target = "car_make")
    @Mapping(source = "carNumber", target = "car_number")
    @Mapping(source = "numberOfSeats", target = "carSeat")
    CarDto carToCarDto(Car car);

}

*/
