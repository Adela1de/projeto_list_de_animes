package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Iterable<User> saveAll(Iterable<User> users){ return userRepository.saveAll( users ); }

    public List<User> findAll(){ return userRepository.findAll(); }

    public User findByIdOrElseThrowResponseStatusException(Long id)
    {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public User saveUser(User user) { return userRepository.save(user); }
}
