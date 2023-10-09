package com.example.housingfinanceloanrate.BatchConfiguration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Primary
    @Bean(value = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.batch-properties")
    public DataSource datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        return dataSource;
    }

    @Bean("platformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    //@Primary
    @Bean(value = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.work-properties")
    public DataSource workDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        return dataSource;
    }
    @Bean("secondPlatformTransactionManager")
    public PlatformTransactionManager secondPlatformTransactionManager(@Qualifier("secondDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
