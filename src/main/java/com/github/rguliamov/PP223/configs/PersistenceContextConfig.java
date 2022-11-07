package com.github.rguliamov.PP223.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Guliamov Rustam
 */
@Configuration
@ComponentScan(basePackages = "com.github.rguliamov")
@EnableTransactionManagement
@ConfigurationProperties(prefix="spring.datasource.hikari")
public class PersistenceContextConfig extends HikariConfig {

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(this);
    }
}
