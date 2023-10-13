package com.example.housingfinanceloanrate.Job;

import com.example.housingfinanceloanrate.Tasklet.RentLoanRateInfoTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JobConfiguration {
    private final DataSource dataSource;
    private final RentLoanRateInfoTasklet rentLoanRateInfoTasklet;
    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    public JobParameters jobParameters() {
        return new JobParametersBuilder()
                .addLocalDateTime("executeDate", LocalDateTime.now())
                .toJobParameters();
    }
    @Bean
    public JobExecution jobExecution() throws Exception {
        return jobLauncher().run(
                new JobBuilder("RentLoanRateInfoJob",jobRepository)
                        .start(rentLoanRateInfoStep())
                        .build()
                ,jobParameters());
    }
    @Bean
    public JobLauncher jobLauncher() throws Exception {
        TaskExecutorJobLauncher taskExecutorJobLauncher = new TaskExecutorJobLauncher();
        taskExecutorJobLauncher.setJobRepository(jobRepository);
        taskExecutorJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        taskExecutorJobLauncher.afterPropertiesSet();
        return taskExecutorJobLauncher;
    }
    @Bean
    public Step rentLoanRateInfoStep() {
        return new StepBuilder("RentLoanRateInfoStep",jobRepository)
                .tasklet(rentLoanRateInfoTasklet,platformTransactionManager)
                .build();
    }
}
