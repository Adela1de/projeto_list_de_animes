package com.example.demo.dtos;

import com.example.demo.entities.Anime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long id;
    @NotEmpty(message = "Name can't be empty")
    @Length(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @NotEmpty
    @Length(min = 3, max = 100, message = "Name must be between 10 and 100 character")
    private String email;
    @NotEmpty
    @Length(min = 3, max = 100, message = "Password must be between 10 and 100 character")
    private String password;
    @JsonIgnore
    private List<Anime> favorites;

}
