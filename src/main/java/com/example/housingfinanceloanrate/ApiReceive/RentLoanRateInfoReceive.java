package com.example.housingfinanceloanrate.ApiReceive;

import com.example.housingfinanceloanrate.DTO.RentLoanRateInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@Slf4j
public class RentLoanRateInfoReceive {
    //@Value("${dataPortal.secret}")
    private String dataPortalSecret = "Rujw%2BIsa8li%2Ba%2FgKuQ2M5xnXH9wNS8evvDQnU1h%2BpRTcm%2BQpzUcAMi7woS1urDmsbRycaM0%2FcBhToF1ut2BQyw%3D%3D";
    private static final String ENDPOINTURL = "https://apis.data.go.kr/B551408/rent-loan-rate-info/rate-list";
    public RentLoanRateInfoResponse apiReceive() {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(ENDPOINTURL)
                .queryParam("serviceKey",dataPortalSecret)
                .queryParam("pageNo","1")
                .queryParam("numOfRows","100")
                .queryParam("dataType","JSON")
                .build();

        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        String finalUrl = uriComponents.toUriString();
        log.info(finalUrl);

        WebClient build = WebClient.builder()
                .uriBuilderFactory(defaultUriBuilderFactory)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        RentLoanRateInfoResponse block = build
                .get()
                .uri(finalUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RentLoanRateInfoResponse.class)
                .block();
        return block;
    }
}
