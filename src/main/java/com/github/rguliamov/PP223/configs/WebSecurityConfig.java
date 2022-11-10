package com.github.rguliamov.PP223.configs;

import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

/**
 * @author Guliamov Rustam
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/user/**").hasRole("USER")
                    .antMatchers("/admin/**").hasAnyRole("ADMIN", "USER")
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .and()
                .formLogin()
                    .loginProcessingUrl("/")
                    .successHandler(authenticationSuccessHandler());
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        CustomAuthenticationSuccessHandler authenticationSuccessHandler = new CustomAuthenticationSuccessHandler();

        return authenticationSuccessHandler;
    }

    /**
     * use custom authentication entities
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * InMemory
     */
    /*@Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$X2iu8Vldrl2owhiNGZ78eOkIXKqOGEjq6HIBCuH1r43ntTleD8bKK")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$X2iu8Vldrl2owhiNGZ78eOkIXKqOGEjq6HIBCuH1r43ntTleD8bKK")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }*/

    /**
     * use standard structure
     */
    /*@Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$X2iu8Vldrl2owhiNGZ78eOkIXKqOGEjq6HIBCuH1r43ntTleD8bKK")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$X2iu8Vldrl2owhiNGZ78eOkIXKqOGEjq6HIBCuH1r43ntTleD8bKK")
                .roles("USER", "ADMIN")
                .build();

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        if (jdbcUserDetailsManager.userExists(user.getUsername())) {
            jdbcUserDetailsManager.deleteUser(user.getUsername());
        }

        if (jdbcUserDetailsManager.userExists(admin.getUsername())) {
            jdbcUserDetailsManager.deleteUser(admin.getUsername());
        }

        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
    }*/


}
