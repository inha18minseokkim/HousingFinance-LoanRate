package com.example.housingfinanceloanrate.ApiReceive;

import com.example.housingfinanceloanrate.DTO.RentLoanRateInfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class RentLoanRateInfoReceiveTest {
    @Autowired
    private RentLoanRateInfoReceive rentLoanRateInfoReceive;
    //Rujw%2BIsa8li%2Ba%2FgKuQ2M5xnXH9wNS8evvDQnU1h%2BpRTcm%2BQpzUcAMi7woS1urDmsbRycaM0%2FcBhToF1ut2BQyw%3D%3D

    @Test
    public void RentLoanRateInfoReceive() {
        RentLoanRateInfoResponse rentLoanRateInfoResponseMono = rentLoanRateInfoReceive.apiReceive();
        RentLoanRateInfoResponse block = rentLoanRateInfoResponseMono;
        System.out.println(block);
    }

}