package com.example.demo.services;

import com.example.demo.entities.Score;
import com.example.demo.entities.pk.ScorePK;
import com.example.demo.repositories.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final AnimeService animeService;
    private final UserService userService;


    public Iterable<Score> saveAll(Iterable<Score> scores){ return scoreRepository.saveAll(scores); }

    public Iterable<Score> findAll(){ return scoreRepository.findAll(); }

    public Score findByIdOrElseThrowResponseStatusException(Long userId, Long animeId)
    {
        var user = userService.findByIdOrElseThrowResponseStatusException(userId);
        var anime = animeService.findByIdOrElseThrowResponseStatusException(animeId);

        var scorePK = new ScorePK(user, anime);

        return scoreRepository.findById(scorePK).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Score could not be found"));
    }

    public Score saveScore(Score score){ return scoreRepository.save(score); }
}
