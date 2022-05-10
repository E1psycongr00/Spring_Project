package com.example.spring_project.repository;

import com.example.spring_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserid(String userid);
    List<User> findAllByName(String name);
    Integer deleteByUserid(String userid);
}