package com.example.test.demo.car.service;


import com.example.test.demo.car.domain.Car;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class CarServiceImp implements CarService{

    private static final Logger log = LogManager.getLogger();

    //@Transactional
    public void outerMethod() {
        log.info("==== outerMethod start ====");
        log.info("==== outerMethod transaction Active : {}",
                TransactionSynchronizationManager.isActualTransactionActive());
        innerMethod();
        log.info("==== outerMethod end ====");
    }

    @Transactional
    public void innerMethod() {
        log.info("==== innerMethod start ====");
        log.info("==== innerMethod transaction Active : {}",
                TransactionSynchronizationManager.isActualTransactionActive());
        log.info("==== innerMethod end ====");
    }


    @Override
    public void carSave(Car car) {

    }
}
