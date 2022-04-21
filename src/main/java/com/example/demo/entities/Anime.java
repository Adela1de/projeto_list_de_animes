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
@Table(name = "tb_anime")
public class Anime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Length(min = 3, max = 100, message = "Name has to have length between 3 and 100")
    private String name;
    private String[] studio;
    private String[] genre;
    private String author;
    @JsonIgnore
    @ManyToMany(mappedBy = "favorites")
    private List<User> users = new ArrayList<>();


    public Anime(Long id, String name, String[] studio, String[] genre, String author)
    {
        this.id = id;
        this.name = name;
        this.studio = studio;
        this.genre = genre;
        this.author = author;
    }
}
