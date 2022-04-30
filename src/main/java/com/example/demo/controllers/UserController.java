package com.example.demo.controllers;

import com.example.demo.dtos.AnimeDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.Anime;
import com.example.demo.mapper.AnimeMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.requests.userrequests.UserGetRequestBody;
import com.example.demo.requests.userrequests.UserPostRequestBody;
import com.example.demo.requests.userrequests.UserPutRequestBody;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

@CrossOrigin("*")
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
        var favoritesDTO =
                favorites.
                stream().
                map(AnimeMapper.INSTANCE::animeDTO).
                collect(Collectors.
                toList());

        return ResponseEntity.ok().body(favoritesDTO);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<UserDTO> findByEmailAndPassword(@RequestBody UserGetRequestBody userGetRequestBody)
    {
        var user = userService.findByEmailAndPassword(userGetRequestBody);
        var userDTO = UserMapper.INSTANCE.toUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserPostRequestBody userPostRequestBody)
    {
        var user = UserMapper.INSTANCE.toUser(userPostRequestBody);
        var userSaved = userService.saveUser(user);
        var userSavedDTO = UserMapper.INSTANCE.toUserDTO(userSaved);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(userSavedDTO.getId()).
                toUri();

        return ResponseEntity.created(uri).body(null);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserPutRequestBody userPutRequestBody)
    {
        var user = UserMapper.INSTANCE.toUser(userPutRequestBody);
        var userUpdated = userService.updateUser(user);
        var userUpdatedDTO = UserMapper.INSTANCE.toUserDTO(userUpdated);

        return ResponseEntity.ok().body(userUpdatedDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "favorites/add/{id}")
    public ResponseEntity<UserDTO> addFavorite(@PathVariable Long id, @RequestBody Anime anime)
    {
        var userUpdated = userService.addFavorites(id, anime);
        var userUpdatedDTO = UserMapper.INSTANCE.toUserDTO(userUpdated);
        return ResponseEntity.ok().body(userUpdatedDTO);
    }

    @PutMapping(path = "favorites/remove/{id}")
    public ResponseEntity<UserDTO> removeFavorite(@PathVariable Long id, @RequestBody Anime anime)
    {
        var userUpdated = userService.removeFavorites(id, anime);
        var userUpdatedDTO = UserMapper.INSTANCE.toUserDTO(userUpdated);
        return ResponseEntity.ok().body(userUpdatedDTO);
    }

}
