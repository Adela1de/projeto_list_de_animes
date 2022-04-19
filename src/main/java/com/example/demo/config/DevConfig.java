package com.example.demo.config;

import com.example.demo.database.DatabaseInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@RequiredArgsConstructor
@Configuration
@Profile("dev")
public class DevConfig {

    private final DatabaseInitializer databaseInitializer;

    @Bean
    public void initiateDatabase(){ databaseInitializer.initDB(); }
}
