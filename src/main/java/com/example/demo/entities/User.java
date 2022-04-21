package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name can't be empty")
    @Length(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @NotEmpty(message = "Email can't be empty")
    @Length(min = 3, max = 100, message = "Name must be between 10 and 100 character")
    private String email;
    @NotEmpty(message = "Password can't be empty")
    @Length(min = 3, max = 100, message = "Password must be between 10 and 100 character")
    private String password;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_user_favorite",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "anime_id"))
    private List<Anime> favorites = new ArrayList<>();

    public User(Long id, String name, String email, String password)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
