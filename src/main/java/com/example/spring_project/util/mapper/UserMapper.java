package com.example.spring_project.util.mapper;

import com.example.spring_project.dto.UserDto;
import com.example.spring_project.entity.User;

public class UserMapper {

    public static User mapper(UserDto userDto) {
        return User.builder()
                .userid(userDto.getUserid())
                .name(userDto.getName())
                .build();
    }

    public static UserDto mapper(User user) {
        return UserDto.builder()
                .userid(user.getUserid())
                .name(user.getName())
                .build();
    }
}
