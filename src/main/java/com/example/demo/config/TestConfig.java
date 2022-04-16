package com.example.demo.config;

import com.example.demo.entities.Anime;
import com.example.demo.services.AnimeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final AnimeService animeService;

    @Override
    public void run(String... args) throws Exception {
        Anime anime1 = new Anime(
                null,
                "Spy x Family",
                new String[]{"Clover Works", "Wit"},
                new String[]{"Comedy", "Romance", "Slice of life"});

        Anime anime2 = new Anime(
                null,
                "One Piece",
                new String[]{"Toei Animation"},
                new String[]{"Comedy", "Adventure", "Shounen"});

        Anime anime3 = new Anime(
                null,
                "Steis;Gate",
                new String[]{"White Fox"},
                new String[]{"Sic-fi", "Thiller", "Drama"});

        animeService.saveAll(Arrays.asList(anime1, anime2, anime3));
    }
}
