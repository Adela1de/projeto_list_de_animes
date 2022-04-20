package com.example.demo.controllers;

import com.example.demo.dtos.AnimeDTO;
import com.example.demo.entities.Anime;
import com.example.demo.mapper.AnimeMapper;
import com.example.demo.services.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        return ResponseEntity.ok().body(allAnimesDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimeDTO> findById(@PathVariable Long id)
    {
        var anime = animeService.findByIdOrElseThrowObjectNotFoundException(id);
        var animeDTO = AnimeMapper.INSTANCE.animeDTO(anime);
        return ResponseEntity.ok().body(animeDTO);
    }
    @PostMapping
    public ResponseEntity<AnimeDTO> addAnime(@RequestBody Anime anime)
    {
        var animeSaved = animeService.saveAnime(anime);
        var animeSavedDTO = AnimeMapper.INSTANCE.animeDTO(animeSaved);
        URI uri =
                ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(anime.getId()).
                toUri();

        return ResponseEntity.created(uri).body(animeSavedDTO);
    }
}
