package com.example.demo.mapper;

import com.example.demo.Requests.ScorePostRequestBody;
import com.example.demo.dtos.ScoreDTO;
import com.example.demo.entities.Score;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ScoreMapper {

    public static final ScoreMapper INSTANCE = Mappers.getMapper(ScoreMapper.class);

    public abstract Score toScore(ScorePostRequestBody scorePostRequestBody);

    public abstract ScoreDTO toScoreDTO(Score score);
}
