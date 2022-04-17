package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Iterable<User> saveAll(Iterable<User> users){ return userRepository.saveAll( users ); }

    public Iterable<User> findAll(){ return userRepository.findAll(); }
}
