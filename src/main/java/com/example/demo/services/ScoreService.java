package com.example.demo.services;

import com.example.demo.entities.Score;
import com.example.demo.repositories.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public Iterable<Score> saveAll(Iterable<Score> scores){ return scoreRepository.saveAll(scores); }

    public Iterable<Score> findAll(){ return scoreRepository.findAll(); }

    public Score saveScore(Score score){ return scoreRepository.save(score); }
}
