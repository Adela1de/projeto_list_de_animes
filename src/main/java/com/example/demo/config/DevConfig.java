package com.example.demo.config;

import com.example.demo.database.DatabaseInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@RequiredArgsConstructor
@Configuration
@Profile("dev")
public class DevConfig {

    private final DatabaseInitializer databaseInitializer;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public void initiateDatabase(){
        if(strategy.equals("create")) databaseInitializer.initDB();
    }
}
