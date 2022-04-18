package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll()
    {
        var allUsers = userService.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id)
    {
        var user = userService.findByIdOrElseThrowResponseStatusException(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        var userSaved = userService.saveUser(user);
        return new  ResponseEntity<User>(userSaved, HttpStatus.CREATED);
    }
}
