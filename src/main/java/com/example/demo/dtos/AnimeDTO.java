package com.example.demo.dtos;

import com.example.demo.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnimeDTO {

    private Long id;
    private String name;
    private String[] studio;
    private String[] genre;
    private String author;
    @JsonIgnore
    private List<User> users;
}
