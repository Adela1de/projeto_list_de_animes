package com.example.demo.mapper;

import com.example.demo.Requests.AnimePostRequestBody;
import com.example.demo.dtos.AnimeDTO;
import com.example.demo.entities.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract AnimeDTO animeDTO(Anime anime);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
}
