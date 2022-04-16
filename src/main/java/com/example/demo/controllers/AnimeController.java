package com.example.demo.controllers;

import com.example.demo.entities.Anime;
import com.example.demo.services.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/animes")
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Iterable<Anime>> findAll()
    {
        var allAnimes = animeService.findAll();
        return ResponseEntity.ok(allAnimes);
    }
}
