package com.example.spring_project.service;


import com.example.spring_project.dto.UserDto;
import com.example.spring_project.dto.response.Response;

public interface UserService {
    Response findAll();
    Response findByUserid(String userid);
    Response insertUser(UserDto userDto);
    Response updateUser(UserDto userDto);
    Response deleteUser(String userid);

}
