package com.example.demo.dtos;

import com.example.demo.entities.pk.ScorePK;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreDTO {

    private ScorePK id;
    private Integer entry;
}
