package com.example.demo.requests.userrequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPutRequestBody {

    private Long id;
    private String name;
    private String email;
    private String password;
}
