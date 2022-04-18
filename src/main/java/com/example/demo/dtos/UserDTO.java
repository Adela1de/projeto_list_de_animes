package com.example.demo.dtos;

import com.example.demo.entities.Anime;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private List<Anime> favorites;

}
