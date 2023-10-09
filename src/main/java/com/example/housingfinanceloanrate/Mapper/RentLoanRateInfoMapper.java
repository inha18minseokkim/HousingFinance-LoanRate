package com.example.housingfinanceloanrate.Mapper;


import com.example.housingfinanceloanrate.DTO.RentLoanRateInfoItem;
import com.example.housingfinanceloanrate.Entity.RentLoanRateInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface RentLoanRateInfoMapper {
    public default Double parseDouble(String target){
        System.out.println("Mapping");
        try{
            return Double.parseDouble(target);
        }catch (NumberFormatException e){
            return null;
        }catch(NullPointerException e){
            return null;
        }
    }
    @Mappings({
            @Mapping(target="id",ignore = true),
            @Mapping(source="bssYmdStart",target="baseStartDate"),
            @Mapping(source="bssYmdEnd",target="baseEndDate"),
            @Mapping(source="organId",target="bankId"),
            @Mapping(target="partialGuaranteeRatio",expression="java(parseDouble(item.getInterest1_1()))"),
            @Mapping(target="baseMoneyRate",expression="java(parseDouble(item.getInterest2_1()))"),
            @Mapping(target="spread",expression="java(parseDouble(item.getInterest3_1()))"),
            @Mapping(target="applyRate",expression="java(parseDouble(item.getInterest4_1()))"),
            @Mapping(target="partialGuaranteeRatio2",expression="java(parseDouble(item.getInterest1_2()))"),
            @Mapping(target="baseMoneyRate2",expression="java(parseDouble(item.getInterest2_2()))"),
            @Mapping(target="spread2",expression="java(parseDouble(item.getInterest3_2()))"),
            @Mapping(target="applyRate2",expression="java(parseDouble(item.getInterest4_2()))")

    })
    RentLoanRateInfo from(RentLoanRateInfoItem item);
}
