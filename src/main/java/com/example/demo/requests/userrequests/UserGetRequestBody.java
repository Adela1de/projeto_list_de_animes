package com.example.demo.requests.userrequests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserGetRequestBody {

    @NotEmpty(message = "Email can't be empty")
    private String email;
    @NotEmpty(message = "PassWord can't be empty")
    private String password;
}
