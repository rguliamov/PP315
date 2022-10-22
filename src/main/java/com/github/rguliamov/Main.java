package com.github.rguliamov;

import com.github.rguliamov.config.PersistenceContextConfig;
import com.github.rguliamov.model.User;
import com.github.rguliamov.service.UserService;
import com.github.rguliamov.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import java.util.Map;

/**
 * @author Guliamov Rustam
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceContextConfig.class);


        UserService service = context.getBean(UserService.class);

        User user = new User("Rustam", "", 24, "");
        service.save(user);
        System.out.println(service.list());
    }
}
