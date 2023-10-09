package com.example.housingfinanceloanrate.Mapper;

import com.example.housingfinanceloanrate.DTO.RentLoanRateInfoItem;
import com.example.housingfinanceloanrate.Entity.RentLoanRateInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RentLoanRateInfoMapperTest {
    @Autowired
    RentLoanRateInfoMapper rentLoanRateInfoMapper;

    @Test
    public void MapingTest(){
        //RentLoanRateInfoItem(bssYmdStart=20230925, bssYmdEnd=20231001, organId=케이뱅크, interest1_1=0,
        // interest2_1=0, interest3_1=0, interest4_1=4.1, interest1_2=0, interest2_2=0, interest3_2=0, interest4_2=0, callCenter=1522-1000)
        RentLoanRateInfoItem item = RentLoanRateInfoItem.builder()
                .bssYmdStart("20230925")
                .bssYmdEnd("20231001")
                .organId("케이뱅크")
                .interest4_1("4.1")
                .callCenter("1522-1000")
                .build();
        RentLoanRateInfo from = rentLoanRateInfoMapper.from(item);
        System.out.println(from);
    }

}