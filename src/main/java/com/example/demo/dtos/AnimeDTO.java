package com.example.demo.dtos;

import com.example.demo.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class AnimeDTO {

    private Long id;
    @NotEmpty
    @Length(min = 3, max = 100, message = "Name has to have length between 3 and 100")
    private String name;
    private String[] studio;
    private String[] genre;
    private String author;
    @JsonIgnore
    private List<User> users;
}
