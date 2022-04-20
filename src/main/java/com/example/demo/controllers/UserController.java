package com.example.demo.controllers;

import com.example.demo.Requests.UserPostRequestBody;
import com.example.demo.dtos.AnimeDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.mapper.AnimeMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<UserDTO>> findAll()
    {
        var allUsersDTO =
                userService.
                findAll().
                stream().
                map(UserMapper.INSTANCE::toUserDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(allUsersDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id)
    {
        var user = userService.findByIdOrElseThrowObjectNotFoundException(id);
        var userDTO = UserMapper.INSTANCE.toUserDTO(user);
        return ResponseEntity.ok().body(userDTO);

    }

    @GetMapping(path = "favorites/{id}")
    public ResponseEntity<Iterable<AnimeDTO>> findFavorites(@PathVariable Long id)
    {
        var favorites = userService.findFavorites(id);
        var favoritesDTO = favorites.stream().map(AnimeMapper.INSTANCE::animeDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(favoritesDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserPostRequestBody userPostRequestBody)
    {
        var user = UserMapper.INSTANCE.toUser(userPostRequestBody);
        var userSaved = userService.saveUser(user);
        var userSavedDTO = UserMapper.INSTANCE.toUserDTO(userSaved);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(userSavedDTO.getId()).
                toUri();

        return ResponseEntity.created(uri).body(userSavedDTO);
    }
}
