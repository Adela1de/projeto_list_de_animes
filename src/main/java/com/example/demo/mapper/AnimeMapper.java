package com.example.demo.mapper;

import com.example.demo.requests.animerequests.AnimePostRequestBody;
import com.example.demo.dtos.AnimeDTO;
import com.example.demo.entities.Anime;
import com.example.demo.requests.animerequests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract AnimeDTO animeDTO(Anime anime);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
