package com.example.demo.database;

import com.example.demo.entities.Anime;
import com.example.demo.entities.Score;
import com.example.demo.entities.User;
import com.example.demo.services.AnimeService;
import com.example.demo.services.ScoreService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final AnimeService animeService;
    private final UserService userService;
    private final ScoreService scoreService;

    public void initDB()
    {
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
                "2241"
        );

        User user2 = new User(
                null,
                "Joao Camara",
                "Joao@Joao.com",
                "6666"
        );

        User user3 = new User(
                null,
                "Nathalia Adelaide",
                "Nathalia@Nathalia.com",
                "3104"
        );

        user1.getFavorites().addAll(Arrays.asList(anime2, anime1));
        user2.getFavorites().add(anime1);
        user3.getFavorites().add(anime2);

        userService.saveAll(Arrays.asList(user1, user2, user3));

        Score score1 = new Score(user1, anime1, 10);
        Score score2 = new Score(user1, anime2, 5);
        Score score3 = new Score(user3, anime2, 8);
        scoreService.saveAll(Arrays.asList(score1, score2, score3));
    }
}
