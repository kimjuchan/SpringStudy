package com.example.test.demo.car.controller;


import com.example.test.demo.car.service.CarServiceImp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarRestController {

    private static final Logger log = LogManager.getLogger();

    public CarRestController(CarServiceImp carServiceImp) {
        this.carServiceImp = carServiceImp;
    }

    private final CarServiceImp carServiceImp;

    @GetMapping("/car/mapstruct")
    public String index(){

        log.info("CarEntity to CarDto Mapstruct start------");
        log.info("CarEntity Info :: ------");
        log.info("CarDto Info------");


        carServiceImp.outerMethod();


        return "index page";

    }



}
