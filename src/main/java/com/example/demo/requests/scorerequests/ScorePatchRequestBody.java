package com.example.demo.requests.scorerequests;

import lombok.Getter;
import lombok.Setter;

public class ScorePatchRequestBody extends ScorePKIdCamps{

    @Getter
    @Setter
    private Integer entry;
}
