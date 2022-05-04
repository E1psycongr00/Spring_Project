package com.example.spring_project.repository;

import com.example.spring_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserid(String userid);
    List<User> findAllByName(String name);
    Long deleteByUserid(String userid);
}