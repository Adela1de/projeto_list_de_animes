package com.example.demo.controllers;

import com.example.demo.dtos.AnimeDTO;
import com.example.demo.entities.Anime;
import com.example.demo.mapper.AnimeMapper;
import com.example.demo.services.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/animes")
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Iterable<AnimeDTO>> findAll()
    {
        var allAnimesDTO =
                animeService.
                findAll().
                stream().
                map(AnimeMapper.INSTANCE::animeDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok(allAnimesDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimeDTO> findById(@PathVariable Long id)
    {
        var anime = animeService.findByIdOrElseThrowObjectNotFoundException(id);
        var animeDTO = AnimeMapper.INSTANCE.animeDTO(anime);
        return ResponseEntity.ok(animeDTO);
    }
    @PostMapping
    public ResponseEntity<AnimeDTO> addAnime(@RequestBody Anime anime)
    {
        var animeSaved = animeService.saveAnime(anime);
        var animeSavedDTO = AnimeMapper.INSTANCE.animeDTO(animeSaved);
        return new ResponseEntity<AnimeDTO>(animeSavedDTO, HttpStatus.CREATED);
    }
}
