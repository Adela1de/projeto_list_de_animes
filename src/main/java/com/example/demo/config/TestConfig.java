package com.example.demo.config;

import com.example.demo.entities.Anime;
import com.example.demo.entities.User;
import com.example.demo.services.AnimeService;
import com.example.demo.services.ScoreService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final AnimeService animeService;
    private final UserService userService;
    private final ScoreService scoreService;

    @Override
    public void run(String... args) throws Exception {
        Anime anime1 = new Anime(
                null,
                "Spy x Family",
                new String[]{"Clover Works", "Wit Studio"},
                new String[]{"Comedy", "Romance", "Slice of life"},
                "Tatsuya Endo"
        );

        Anime anime2 = new Anime(
                null,
                "One Piece",
                new String[]{"Toei Animation"},
                new String[]{"Comedy", "Shounen", "Adventure"},
                "Eichiro Oda"
        );

        Anime anime3 = new Anime(
                null,
                "Steins;Gate",
                new String[]{"White Fox"},
                new String[]{"Sci-fi", "Thriller", "Drama"},
                "Jukki Hanada"
        );

        animeService.saveAll(Arrays.asList(anime1, anime2, anime3));

        User user1 = new User(
                null,
                "Luiz Adelaide",
                "Luiz@Luiz.com",
                "2241",
                Arrays.asList(anime2, anime1)
        );

        User user2 = new User(
                null,
                "Joao Camara",
                "Joao@Joao.com",
                "6666",
                Arrays.asList(anime1)
        );

        User user3 = new User(
                null,
                "Nathalia Adelaide",
                "Nathalia@Nathalia.com",
                "3104",
                Arrays.asList(anime2)
        );


    }
}
