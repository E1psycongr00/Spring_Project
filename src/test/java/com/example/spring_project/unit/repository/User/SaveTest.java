package com.example.spring_project.unit.repository.User;


import com.example.spring_project.entity.User;
import com.example.spring_project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class SaveTest {

    @Autowired
    UserRepository userRepository;

    @Nested
    @DisplayName("Save 리턴 테스트")
    class saveTest {

        @Test
        @DisplayName("save 성공시 리턴 User")
        void SuccessTest() {
            User user = User.builder()
                    .name("123")
                    .userid("aa1")
                    .build();

            User x = userRepository.save(user);
            assertThat(x.getUserid()).isEqualTo(user.getUserid());
        }

        @Test
        @DisplayName("Save 실패시 throw")
        void FailTest() {
            // IllegalArgumentException   nested exception 이란?? 통과 못하는 이유가 뭐지??
//            assertThatThrownBy(() -> userRepository.save(null)).isInstanceOf(IllegalArgumentException.class);

            assertThatThrownBy(() -> userRepository.save(null))
                    .isInstanceOf(InvalidDataAccessApiUsageException.class);

        }
    }

}
