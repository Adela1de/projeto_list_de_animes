package com.example.demo.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScorePostRequestBody {

    private Long userId;
    private Long animeId;
    private Integer entry;
}
