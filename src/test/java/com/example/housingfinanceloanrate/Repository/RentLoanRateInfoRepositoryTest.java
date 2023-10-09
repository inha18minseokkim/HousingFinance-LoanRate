package com.example.housingfinanceloanrate.Repository;

import com.example.housingfinanceloanrate.Entity.RentLoanRateInfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentLoanRateInfoRepositoryTest {
    @Autowired
    private RentLoanRateInfoRepository repository;
//    @MockBean
//    private HousingFinanceLoanRateJobConfiguration config;

    @Test
    @Rollback(value = false)
    public void insertTest() {
        RentLoanRateInfo rentLoanRateInfo = RentLoanRateInfo.builder()
                .id(333L)
                .applyRate(1.0)
                .baseMoneyRate(4.0)
                .bankId("TEST")
                .build();
        repository.findAll().forEach(System.out::println);
        System.out.println(rentLoanRateInfo);

        repository.save(rentLoanRateInfo);
    }
}