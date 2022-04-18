package com.example.demo.services;

import com.example.demo.entities.Anime;
import com.example.demo.repositories.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Iterable<Anime> saveAll( Iterable<Anime> anime ) { return animeRepository.saveAll( anime ); }

    public List<Anime> findAll(){ return animeRepository.findAll(); }

    public Anime findByIdOrElseThrowResponseStatusException(Long id)
    {
        return animeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime saveAnime(Anime anime){ return animeRepository.save(anime); }
}
