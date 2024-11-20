package com.taskmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.taskmanager.service.TaskService;

/**
 * Security configuration for the Task Manager application.
 * Defines authentication and authorization rules.
 */

@Configuration
public class SecurityConfig {
	
	@Autowired
	TaskService taskService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity (optional)
            .authorizeRequests()
            .requestMatchers("/tasks/**").hasRole("ADMIN") // Secure the tasks endpoints with ADMIN role
            .anyRequest().permitAll() // Allow other endpoints without authentication
            .and()
            .httpBasic(); // Enable HTTP Basic Authentication

        return http.build();
    }
}