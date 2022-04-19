package com.example.demo.config;

import com.example.demo.database.DatabaseInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile("test")
public class TestConfig{

    private final DatabaseInitializer databaseInitializer;

    @Bean
    public void initiateDatabase(){ databaseInitializer.initDB(); }
}
