package com.example.demo.requests.scorerequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreGetRequestBody {

    private Long userId;
    private Long animeId;
}
