package com.example.spring_project.unit.repository.User;


import com.example.spring_project.entity.User;
import com.example.spring_project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class DeleteByUseridTest {

    @Autowired
    UserRepository userRepository;


    @Nested
    @DisplayName("기능 테스트")
    class DeleteTest {

        @Test
        @DisplayName("delete 싪패시 throw error")
        void failTest() {
            String userid = "1";

        }

        @Test
        @DisplayName("delete 성공시 1 반환")
        void successTest() {
            User user = User.builder()
                    .userid("1")
                    .name("hello")
                    .build();

            userRepository.save(user);

        }
    }
}
