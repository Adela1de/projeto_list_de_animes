package com.example.demo.requests.userrequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetRequestBody {

    private String email;
    private String password;
}
