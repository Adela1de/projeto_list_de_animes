package com.example.demo.services;

import com.example.demo.entities.Anime;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Anime saveAnime(Anime anime)
    {
        anime.setId(null);
        return animeRepository.save(anime);
    }

    public Anime updateAnime(Anime anime)
    {
        findByIdOrElseThrowObjectNotFoundException(anime.getId());
        return animeRepository.save(anime);
    }

    public void deleteAnime(Long id)
    {
        findByIdOrElseThrowObjectNotFoundException(id);
        try{
            animeRepository.deleteById(id);
        }catch(DataIntegrityViolationException e)
        {
            throw new com.example.demo.exceptions.DataIntegrityViolationException(
                    "Object can't be deleted, there is users associated with it objectId:"
                    +id+
                    " Type:"
                    + Anime.class.getName());
        }
    }

}
