package com.example.demo.services;

import com.example.demo.entities.Anime;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.userrequests.UserGetRequestBody;
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

    public User findByEmailAndPassword(UserGetRequestBody userGetRequestBody)
    {
        return userRepository.findByEmailAndPassword(userGetRequestBody.getEmail(), userGetRequestBody.getPassword());
    }

    public List<Anime> findFavorites(Long id)
    {
        var user = findByIdOrElseThrowObjectNotFoundException(id);
        return user.getFavorites();
    }

    public User saveUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public User updateUser(User user)
    {
        findByIdOrElseThrowObjectNotFoundException(user.getId());
        return userRepository.save(user);
    }

    public void deleteUser(Long id)
    {
        findByIdOrElseThrowObjectNotFoundException(id);
        userRepository.deleteById(id);
    }

    public User addFavorites(Long id, Anime anime)
    {
        var user = findByIdOrElseThrowObjectNotFoundException(id);
        user.getFavorites().add(anime);
        return userRepository.save(user);
    }

}
