package com.example.demo.dtos;

import com.example.demo.entities.Anime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    @JsonIgnore
    private List<Anime> favorites;

}
