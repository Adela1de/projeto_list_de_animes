package com.example.demo.controllers;

import com.example.demo.entities.Score;
import com.example.demo.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping
    public ResponseEntity<Iterable<Score>> findAll()
    {
        var allScores = scoreService.findAll();
        return ResponseEntity.ok(allScores);
    }
}
