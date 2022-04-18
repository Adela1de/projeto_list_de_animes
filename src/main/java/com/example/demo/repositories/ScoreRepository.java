package com.example.demo.repositories;

import com.example.demo.entities.Score;
import com.example.demo.entities.pk.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
