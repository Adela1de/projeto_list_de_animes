package com.example.demo.requests.scorerequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScorePostRequestBody {

    private Long userId;
    private Long animeId;
    private Integer entry;
}
