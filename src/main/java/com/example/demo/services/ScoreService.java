package com.example.demo.services;

import com.example.demo.Requests.ScoreGetRequestBody;
import com.example.demo.Requests.ScorePostRequestBody;
import com.example.demo.entities.Score;
import com.example.demo.entities.pk.ScorePK;
import com.example.demo.repositories.AnimeRepository;
import com.example.demo.repositories.ScoreRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;


    public Iterable<Score> saveAll(Iterable<Score> scores){ return scoreRepository.saveAll(scores); }

    public List<Score> findAll(){ return scoreRepository.findAll(); }

    public List<Score> findByUser(Long id)
    {

        var userList = scoreRepository.findByUser(id);
        return userList;
    }

    public Score findByIdOrElseThrowResponseStatusException(ScoreGetRequestBody scoreGetRequestBody)
    {
        var user = userRepository.findById(scoreGetRequestBody.getUserId()).get();
        var anime = animeRepository.findById(scoreGetRequestBody.getAnimeId()).get();

        var scorePK = new ScorePK(user, anime);

        return scoreRepository.findById(scorePK).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Score could not be found"));
    }

    public Score saveScore(ScorePostRequestBody scorePostRequestBody)
    {
        var user = userRepository.findById(scorePostRequestBody.getUserId()).get();
        var anime = animeRepository.findById(scorePostRequestBody.getAnimeId()).get();
        var entry = scorePostRequestBody.getEntry();
        return scoreRepository.save(new Score(user, anime, entry));
    }
}
