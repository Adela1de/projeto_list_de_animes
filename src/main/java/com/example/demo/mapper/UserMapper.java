package com.example.demo.mapper;

import com.example.demo.requests.userrequests.UserPostRequestBody;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.requests.userrequests.UserPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract UserDTO toUserDTO(User user);

    public abstract User toUser(UserPostRequestBody userPostRequestBody);

    public abstract User toUser(UserPutRequestBody userPutRequestBody);
}
