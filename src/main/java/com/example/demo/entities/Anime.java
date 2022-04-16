package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_anime")
public class Anime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String[] studio;
    private String[] genre;
    private String autor;
    @JsonIgnore
    @ManyToMany(mappedBy = "favorites")
    private List<User> users = new ArrayList<>();


    public Anime(Long id, String name, String[] studio, String[] genre, String autor)
    {
        this.id = id;
        this.name = name;
        this.studio = studio;
        this.genre = genre;
        this.autor = autor;
    }
}
