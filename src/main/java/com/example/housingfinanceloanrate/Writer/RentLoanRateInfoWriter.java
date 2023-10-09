package com.example.housingfinanceloanrate.Writer;

import com.example.housingfinanceloanrate.Entity.RentLoanRateInfo;
import com.example.housingfinanceloanrate.Repository.RentLoanRateInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class RentLoanRateInfoWriter {
    private final RentLoanRateInfoRepository rentLoanRateInfoRepository;
    public Integer save(RentLoanRateInfo entity){
        log.info(entity.toString());
        rentLoanRateInfoRepository.save(entity);
        return 1;
    }
}
