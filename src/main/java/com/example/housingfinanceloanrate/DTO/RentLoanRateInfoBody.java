package com.example.housingfinanceloanrate.DTO;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@ToString
@Data
@Builder
@AllArgsConstructor
public class RentLoanRateInfoBody {
    private Long pageNo;
    private Long totalCount;
    private Long numOfRows;
    private List<RentLoanRateInfoItem> items;
}
