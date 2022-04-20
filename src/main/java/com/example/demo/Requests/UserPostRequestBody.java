package com.example.demo.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostRequestBody {

    private Long id;
    private String name;
    private String email;
    private String password;

}
