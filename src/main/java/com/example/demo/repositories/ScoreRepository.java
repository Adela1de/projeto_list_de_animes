package com.example.demo.repositories;

import com.example.demo.entities.Score;
import com.example.demo.entities.pk.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

    @Query(value = "SELECT * FROM tb_score u where u.user_id = ?", nativeQuery = true)
    List<Score> findByUser(Long id);
}
