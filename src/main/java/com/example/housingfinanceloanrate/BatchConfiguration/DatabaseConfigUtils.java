package com.example.housingfinanceloanrate.BatchConfiguration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

public class DatabaseConfigUtils {
    public static final String BASE_PACKAGE = "com.example.housingfinanceloanrate";
    public static final String ENTITY_PACKAGE = BASE_PACKAGE + ".Entity";
    public static final String REPOSITORY_PACKAGE = BASE_PACKAGE + ".Repository";


    private DatabaseConfigUtils() {
        throw new IllegalStateException("Utility class");
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

    static Properties jpaProperties() {
        Properties properties = new Properties();
        System.out.println("@@@@@@@@@@@@@@@");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.use_sql_comments", "false");
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");

        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");

        properties.setProperty("hibernate.jdbc.batch_size", "5000");
        properties.setProperty("hibernate.order_inserts", "true");
        properties.setProperty("hibernate.order_updates", "true");
        properties.setProperty("hibernate.jdbc.batch_versioned_data", "true");

        properties.setProperty("spring.jpa.hibernate.jdbc.batch_size", "5000");
        properties.setProperty("spring.jpa.hibernate.order_inserts", "true");
        properties.setProperty("spring.jpa.hibernate.order_updates", "true");


        //properties.setProperty("spring.batch.jdbc.initialize-schema", "always");
        //properties.setProperty("spring.batch.initialize-schema", "always");
        //properties.setProperty("spring.jpa.hibernate.ddl-auto", "create");

        properties.setProperty("spring.jpa.hibernate.jdbc.batch_versioned_data", "true");
        properties.setProperty("spring.jpa.properties.hibernate.jdbc.batch_size", "5000");
        properties.setProperty("spring.jpa.properties.hibernate.order_inserts", "true");
        properties.setProperty("spring.jpa.properties.hibernate.order_updates", "true");
        properties.setProperty("spring.jpa.properties.hibernate.jdbc.batch_versioned_data", "true");
        return properties;
    }

    static JpaVendorAdapter jpaVendorAdapters() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
        //hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");



        return hibernateJpaVendorAdapter;
    }

}