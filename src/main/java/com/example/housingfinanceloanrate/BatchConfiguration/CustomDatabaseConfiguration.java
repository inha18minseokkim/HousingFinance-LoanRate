package com.example.housingfinanceloanrate.BatchConfiguration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static com.example.housingfinanceloanrate.BatchConfiguration.DatabaseConfigUtils.*;

@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = DatabaseConfigUtils.REPOSITORY_PACKAGE)
@Configuration
public class CustomDatabaseConfiguration {

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("secondDataSource") DataSource dataSource) {
        return DatabaseConfigUtils.entityManagerFactoryBean("custom", dataSource);
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return DatabaseConfigUtils.jpaTransactionManager(entityManagerFactory.getObject());
    }
    public static LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            String persistenceUnitName, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName(persistenceUnitName);
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapters());
        emf.setPackagesToScan(ENTITY_PACKAGE);
        emf.setJpaProperties(jpaProperties());

        return emf;
    }

    public static JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setJpaDialect(new HibernateJpaDialect());

        return jpaTransactionManager;
    }
}