package com.example.demo.controllers;

import com.example.demo.Requests.ScoreGetRequestBody;
import com.example.demo.Requests.ScorePostRequestBody;
import com.example.demo.dtos.ScoreDTO;
import com.example.demo.entities.Score;
import com.example.demo.mapper.ScoreMapper;
import com.example.demo.services.AnimeService;
import com.example.demo.services.ScoreService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/scores")
public class ScoreController {

    private final ScoreService scoreService;
    private final AnimeService animeService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<ScoreDTO>> findAll()
    {
        var allScoresDTO =
                scoreService.
                findAll().
                stream().
                map(ScoreMapper.INSTANCE::toScoreDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok(allScoresDTO);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<ScoreDTO> findById(@RequestBody ScoreGetRequestBody scoreGetRequestBody)
    {
        var userId = scoreGetRequestBody.getUserId();
        var animeId = scoreGetRequestBody.getAnimeId();
        var score = scoreService.findByIdOrElseThrowResponseStatusException(userId, animeId);
        var scoreDTO = ScoreMapper.INSTANCE.toScoreDTO(score);

        return ResponseEntity.ok(scoreDTO);
    }

    @PostMapping
    public ResponseEntity<ScoreDTO> addScore(@RequestBody ScorePostRequestBody scorePostRequestBody)
    {
        var user = userService.findByIdOrElseThrowResponseStatusException(scorePostRequestBody.getUserId());
        var anime = animeService.findByIdOrElseThrowResponseStatusException(scorePostRequestBody.getAnimeId());
        var entry = scorePostRequestBody.getEntry();
        var savedScore = scoreService.saveScore(new Score(user, anime, entry));
        var savedScoreDTO = ScoreMapper.INSTANCE.toScoreDTO(savedScore);
        return new ResponseEntity<ScoreDTO>(savedScoreDTO, HttpStatus.CREATED);
    }
}
