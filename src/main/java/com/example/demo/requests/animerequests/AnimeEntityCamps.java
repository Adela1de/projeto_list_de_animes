package com.example.demo.requests.animerequests;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AnimeEntityCamps {

    private Long id;
    @NotEmpty
    @Length(min = 3, max = 100, message = "Name has to have length between 3 and 100")
    private String name;
    private String[] studio;
    private String[] genre;
    private String author;

}
