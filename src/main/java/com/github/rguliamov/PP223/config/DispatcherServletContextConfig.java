package com.github.rguliamov.PP223.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import java.util.Locale;

/**
 * @author Guliamov Rustam
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.github.rguliamov.PP223.presentation.controller")
public class DispatcherServletContextConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;
}
