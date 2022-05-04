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
public class FindByUseridTest {
    @Autowired
    UserRepository userRepository;


    @Nested
    @DisplayName("리턴 테스트")
    public class ReturnTypeTest {

        @Test
        @DisplayName("아무 것도 조회하지 않은 경우 null 반환")
        public void emptyTest() {

            User user = userRepository.findByUserid("hello");
            assertThat(user).isEqualTo(null);
        }

        @Test
        @DisplayName("존재 할 경우 User 반환")
        public void UserTest() {
            // given
            User user1 = User.builder()
                    .userid("hello")
                    .name("haha1")
                    .build();
            userRepository.save(user1);
            User user = userRepository.findByUserid("hello");
            assertThat(user).isInstanceOf(User.class);
        }
    }
}
