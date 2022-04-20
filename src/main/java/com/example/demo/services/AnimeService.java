package com.example.demo.services;

import com.example.demo.entities.Anime;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Iterable<Anime> saveAll( Iterable<Anime> anime ) { return animeRepository.saveAll( anime ); }

    public List<Anime> findAll(){ return animeRepository.findAll(); }

    public Anime findByIdOrElseThrowObjectNotFoundException(Long id)
    {
        return animeRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Anime not found! ID: " + id + " type: " + Anime.class.getName()));
    }

    public Anime saveAnime(Anime anime){ return animeRepository.save(anime); }
}
