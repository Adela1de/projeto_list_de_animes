package com.example.demo.controllers;

import com.example.demo.Requests.ScoreGetRequestBody;
import com.example.demo.Requests.ScorePostRequestBody;
import com.example.demo.dtos.ScoreDTO;
import com.example.demo.mapper.ScoreMapper;
import com.example.demo.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping
    public ResponseEntity<Iterable<ScoreDTO>> findAll()
    {
        var allScoresDTO =
                scoreService.
                findAll().
                stream().
                map(ScoreMapper.INSTANCE::toScoreDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(allScoresDTO);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<ScoreDTO> findById(@RequestBody ScoreGetRequestBody scoreGetRequestBody)
    {
        var score =
                scoreService.
                        findByIdOrElseThrowObjectNotFoundException(scoreGetRequestBody);

        var scoreDTO = ScoreMapper.INSTANCE.toScoreDTO(score);

        return ResponseEntity.ok().body(scoreDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Iterable<ScoreDTO>> findByUser(@PathVariable Long id)
    {
        var userScoresDTO =
                scoreService.
                findByUser(id).
                stream().
                map(ScoreMapper.INSTANCE::toScoreDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(userScoresDTO);

    }

    @PostMapping
    public ResponseEntity<ScoreDTO> addScore(@RequestBody ScorePostRequestBody scorePostRequestBody)
    {
        var savedScore = scoreService.saveScore(scorePostRequestBody);
        var savedScoreDTO = ScoreMapper.INSTANCE.toScoreDTO(savedScore);
        URI uri =
                ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedScoreDTO.getId().getUser().getId()).
                toUri();

        return ResponseEntity.created(uri).body(savedScoreDTO);
    }
}
