package com.example.demo.controllers;

import com.example.demo.Requests.ScoreGetRequestBody;
import com.example.demo.Requests.ScorePostRequestBody;
import com.example.demo.entities.Score;
import com.example.demo.services.AnimeService;
import com.example.demo.services.ScoreService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/scores")
public class ScoreController {

    private final ScoreService scoreService;
    private final AnimeService animeService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Score>> findAll()
    {
        var allScores = scoreService.findAll();
        return ResponseEntity.ok(allScores);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Score> findById(@RequestBody ScoreGetRequestBody scoreGetRequestBody)
    {
        var userId = scoreGetRequestBody.getUserId();
        var animeId = scoreGetRequestBody.getAnimeId();
        var score = scoreService.findByIdOrElseThrowResponseStatusException(userId, animeId);

        return ResponseEntity.ok(score);
    }

    @PostMapping
    public ResponseEntity<Score> addScore(@RequestBody ScorePostRequestBody scorePostRequestBody)
    {
        var user = userService.findByIdOrElseThrowResponseStatusException(scorePostRequestBody.getUserId());
        var anime = animeService.findByIdOrElseThrowResponseStatusException(scorePostRequestBody.getAnimeId());
        var entry = scorePostRequestBody.getEntry();
        var savedScore = scoreService.saveScore(new Score(user, anime, entry));
        return new ResponseEntity<Score>(savedScore, HttpStatus.CREATED);
    }
}
