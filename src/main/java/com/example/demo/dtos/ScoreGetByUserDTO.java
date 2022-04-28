package com.example.demo.dtos;

import com.example.demo.entities.Anime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreGetByUserDTO {

    private Anime anime;
    private Integer entry;
}
