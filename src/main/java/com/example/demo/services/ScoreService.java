package com.example.demo.services;

import com.example.demo.Requests.ScoreGetRequestBody;
import com.example.demo.Requests.ScorePostRequestBody;
import com.example.demo.entities.Anime;
import com.example.demo.entities.Score;
import com.example.demo.entities.User;
import com.example.demo.entities.pk.ScorePK;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.AnimeRepository;
import com.example.demo.repositories.ScoreRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;


    public Iterable<Score> saveAll(Iterable<Score> scores){ return scoreRepository.saveAll(scores); }

    public List<Score> findAll(){ return scoreRepository.findAll(); }

    public List<Score> findByUser(Long id)
    {
        var userList = scoreRepository.findByUser(id);
        if(userList.isEmpty())
            throw new ObjectNotFoundException("User does not exist! ID: " + id + " type: " + User.class.getName());
        return userList;
    }

    public Score findByIdOrElseThrowObjectNotFoundException(ScoreGetRequestBody scoreGetRequestBody)
    {
        User user;
        Anime anime;
        try{
            user = userRepository.findById(scoreGetRequestBody.getUserId()).get();
            anime = animeRepository.findById(scoreGetRequestBody.getAnimeId()).get();
        }catch (NoSuchElementException e)
        {
            throw new ObjectNotFoundException(
                    "User with Id: " +
                    scoreGetRequestBody.getUserId()+
                    " or Anime with Id: "+
                    scoreGetRequestBody.getAnimeId()+
                    " does not exist"
            );
        }

        var scorePK = new ScorePK(user, anime);

        return scoreRepository.findById(scorePK).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Score could not be found for userId: "+
                        scoreGetRequestBody.getUserId() +
                        " and animeId: "+
                        scoreGetRequestBody.getAnimeId())
        );
    }

    public Score saveScore(ScorePostRequestBody scorePostRequestBody)
    {
        var user = userRepository.findById(scorePostRequestBody.getUserId()).get();
        var anime = animeRepository.findById(scorePostRequestBody.getAnimeId()).get();
        var entry = scorePostRequestBody.getEntry();
        return scoreRepository.save(new Score(user, anime, entry));
    }
}
