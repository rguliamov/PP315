package com.github.rguliamov.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Guliamov Rustam
 */
@Configuration
@ComponentScan(basePackages = "com.github.rguliamov")
@EnableTransactionManagement
public class PersistenceContextConfig {

    @Bean
    public DataSource dataSource() {

        HikariDataSource dataSource = new HikariDataSource(new HikariConfig(loadDataSourceProperties()));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPackagesToScan("com.github.rguliamov.model");

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        em.setJpaProperties(loadJPAProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @SneakyThrows
    private Properties loadJPAProperties() {
        Properties properties = new Properties();

        properties.load(getClass().getClassLoader().getResourceAsStream("jpa.properties"));

        return properties;
    }

    @SneakyThrows
    private Properties loadDataSourceProperties() {
        Properties properties = new Properties();

        properties.load(getClass().getClassLoader().getResourceAsStream("hikaridatasource.properties"));

        return properties;
    }
}
