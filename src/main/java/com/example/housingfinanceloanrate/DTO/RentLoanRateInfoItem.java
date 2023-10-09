package com.example.housingfinanceloanrate.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class RentLoanRateInfoItem {
    private String bssYmdStart;	//기준년월 START
    private String bssYmdEnd;	//기준년월 END
    private String organId;	//은행명
    private String interest1_1;	//부분보증비율1
    private String interest2_1;	//기준금리1
    private String interest3_1;	//가산금리1
    private String interest4_1;	//적용금리1
    private String interest1_2;	//부분보증비율2
    private String interest2_2;	//기준금리2
    private String interest3_2;	//가산금리2
    private String interest4_2;	//적용금리2
    private String callCenter;	//고객센터
}
