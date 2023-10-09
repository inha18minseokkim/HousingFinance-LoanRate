package com.example.housingfinanceloanrate.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class RentLoanRateInfoResponse {
    private RentLoanRateInfoHeader header;
    private RentLoanRateInfoBody body;
}
