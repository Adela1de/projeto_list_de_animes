package com.example.demo.controllers;

import com.example.demo.entities.Anime;
import com.example.demo.services.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/animes")
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Iterable<Anime>> findAll()
    {
        var allAnimes = animeService.findAll();
        return ResponseEntity.ok(allAnimes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id)
    {
        var anime = animeService.findByIdOrElseThrowResponseStatusException(id);
        return ResponseEntity.ok(anime);
    }
    @PostMapping
    public ResponseEntity<Anime> addAnime(@RequestBody Anime anime)
    {
        var animeSaved = animeService.saveAnime(anime);
        return new ResponseEntity<Anime>(animeSaved, HttpStatus.CREATED);
    }
}
