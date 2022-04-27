package com.example.demo.requests.userrequests;

import com.example.demo.entities.Anime;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPutRequestBody  extends UserEntityCamps{

    private List<Anime> favorites;
}
