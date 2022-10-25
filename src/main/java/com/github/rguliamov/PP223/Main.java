package com.github.rguliamov.PP223;

import com.github.rguliamov.PP223.config.PersistenceContextConfig;
import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Guliamov Rustam
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
