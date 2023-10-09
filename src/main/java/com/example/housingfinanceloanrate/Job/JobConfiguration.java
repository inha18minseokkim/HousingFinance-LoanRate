package com.example.housingfinanceloanrate.Job;

import com.example.housingfinanceloanrate.Tasklet.RentLoanRateInfoTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JobConfiguration {
    @Qualifier("secondDataSource")
    private final DataSource dataSource;
    private final RentLoanRateInfoTasklet rentLoanRateInfoTasklet;
    @Qualifier("secondPlatformTransactionManager")
    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;
    @Bean
    public Job rentLoanRateInfoJob() {

        return new JobBuilder("RentLoanRateInfoJob",jobRepository)
                .start(rentLoanRateInfoStep())
                .build();
    }
    @Bean
    public Step rentLoanRateInfoStep() {
        return new StepBuilder("RentLoanRateInfoStep",jobRepository)
                .tasklet(rentLoanRateInfoTasklet,platformTransactionManager)
                .build();
    }
}
