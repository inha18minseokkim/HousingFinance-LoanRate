package com.example.housingfinanceloanrate.Tasklet;

import com.example.housingfinanceloanrate.ApiReceive.RentLoanRateInfoReceive;
import com.example.housingfinanceloanrate.DTO.RentLoanRateInfoResponse;
import com.example.housingfinanceloanrate.Entity.RentLoanRateInfo;
import com.example.housingfinanceloanrate.Mapper.RentLoanRateInfoMapper;
import com.example.housingfinanceloanrate.Repository.RentLoanRateInfoRepository;
import com.example.housingfinanceloanrate.Writer.RentLoanRateInfoWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentLoanRateInfoTasklet implements Tasklet {
    private final RentLoanRateInfoReceive receive;
    private final RentLoanRateInfoMapper mapper;
    private final RentLoanRateInfoWriter writer;
    private final RentLoanRateInfoRepository repository;
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        RentLoanRateInfoResponse response = receive.apiReceive();
        if(!response.getHeader().getResultCode().equals("00")){
            log.error("Api 수신 실패");
            chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.FAILED);
            return RepeatStatus.FINISHED;
        }
        Stream<RentLoanRateInfo> rentLoanRateInfoStream = response.getBody().getItems().stream().map(mapper::from);
        rentLoanRateInfoStream.forEach(writer::save);
        log.info("@@"+repository.findAll().size());

        return RepeatStatus.FINISHED;
    }
}
