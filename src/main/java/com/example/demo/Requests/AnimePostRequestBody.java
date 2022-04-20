package com.example.demo.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimePostRequestBody {

    private Long id;
    private String name;
    private String[] studio;
    private String[] genre;
    private String author;

}
