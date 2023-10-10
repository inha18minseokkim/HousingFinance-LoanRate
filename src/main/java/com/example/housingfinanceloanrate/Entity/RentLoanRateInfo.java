package com.example.housingfinanceloanrate.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "BankIdAndBaseDate", columnNames = {"bankId", "baseStartDate","baseEndDate"})
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentLoanRateInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String baseStartDate;
    private String baseEndDate;
    private String bankId;
    private Double partialGuaranteeRatio;
    private Double baseMoneyRate;
    private Double spread;
    private Double applyRate;
    private Double partialGuaranteeRatio2;
    private Double baseMoneyRate2;
    private Double spread2;
    private Double applyRate2;
    private String callCenter;
}
