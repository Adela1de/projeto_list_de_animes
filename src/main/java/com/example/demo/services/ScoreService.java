package com.example.demo.services;

import com.example.demo.entities.Anime;
import com.example.demo.entities.Score;
import com.example.demo.entities.User;
import com.example.demo.entities.pk.ScorePK;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.AnimeRepository;
import com.example.demo.repositories.ScoreRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.scorerequests.ScorePKIdCamps;
import com.example.demo.requests.scorerequests.ScorePostRequestBody;
import com.example.demo.requests.scorerequests.ScorePutRequestBody;
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

    public Score findByIdOrElseThrowObjectNotFoundException(ScorePKIdCamps scorePKIdCamps)
    {
        var scorePK = userAndAnimeExists(scorePKIdCamps);
        return scoreRepository.findById(scorePK).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Score could not be found for userId: "+
                        scorePK.getUser().getId() +
                        " and animeId: "+
                        scorePK.getAnime().getId())
        );
    }

    public Score saveScore(ScorePostRequestBody scorePostRequestBody)
    {
        var scorePK = userAndAnimeExists(scorePostRequestBody);
        var entry = scorePostRequestBody.getEntry();
        return scoreRepository.save(new Score(scorePK.getUser(), scorePK.getAnime(), entry));
    }

    public void deleteScore(ScorePKIdCamps scorePKIdCamps)
    {
        var score = findByIdOrElseThrowObjectNotFoundException(scorePKIdCamps);
        scoreRepository.deleteById(score.getId());
    }

    public Score updateEntry(ScorePutRequestBody scorePutRequestBody)
    {
        var scoreToBeUpdated = findByIdOrElseThrowObjectNotFoundException(scorePutRequestBody);
        scoreToBeUpdated.setEntry(scorePutRequestBody.getEntry());

        return scoreRepository.save(scoreToBeUpdated);
    }

    public ScorePK userAndAnimeExists(ScorePKIdCamps scorePKIdCamps)
    {
        User user;
        Anime anime;
        try{
            user = userRepository.findById(scorePKIdCamps.getUserId()).get();
            anime = animeRepository.findById(scorePKIdCamps.getAnimeId()).get();
        }catch (NoSuchElementException e)
        {
            throw new ObjectNotFoundException(
                    "User with Id: " +
                            scorePKIdCamps.getUserId()+
                            " or Anime with Id: "+
                            scorePKIdCamps.getAnimeId()+
                            " does not exist"
            );
        }

        return new ScorePK(user, anime);
    }

}
