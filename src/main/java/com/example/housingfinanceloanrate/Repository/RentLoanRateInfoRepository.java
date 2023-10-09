package com.example.housingfinanceloanrate.Repository;

import com.example.housingfinanceloanrate.Entity.RentLoanRateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentLoanRateInfoRepository extends JpaRepository<RentLoanRateInfo,Long> {
}
