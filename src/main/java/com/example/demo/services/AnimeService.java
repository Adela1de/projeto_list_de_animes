package com.example.demo.services;

import com.example.demo.entities.Anime;
import com.example.demo.repositories.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Iterable<Anime> saveAll( Iterable<Anime> anime ) { return animeRepository.saveAll( anime ); }

    public Iterable<Anime> findAll(){ return animeRepository.findAll(); }

}
