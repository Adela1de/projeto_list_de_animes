package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Iterable<User> saveAll(Iterable<User> users){ return userRepository.saveAll( users ); }

    public List<User> findAll(){ return userRepository.findAll(); }

    public User findByIdOrElseThrowObjectNotFoundException(Long id)
    {
        return userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User not found! ID: " + id + " type: " + User.class.getName()));
    }

    public User saveUser(User user) { return userRepository.save(user); }
}
