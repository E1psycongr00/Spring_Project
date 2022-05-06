package com.example.spring_project.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 접근자를 private으로 설정
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long msrl;

    @Column(nullable = false, unique = true, length = 30)
    private String userid;

    @Column(nullable = false, length = 100)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

}