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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FindAllByNameTest {

    @Autowired
    UserRepository userRepository;


    @Nested
    @DisplayName("기능 테스트")
    class findAllTest {

        @Test
        @DisplayName("List<User> 타입이 맞는가")
        void finAllByUseridTest1() {
            // given
            User user1 = User.builder()
                    .userid("hello")
                    .name("haha1")
                    .build();

            User user2 = User.builder()
                    .userid("hello2")
                    .name("haha1")
                    .build();
            userRepository.save(user1);
            userRepository.save(user2);

            // when
            List<User> userList = userRepository.findAllByName("haha1");

            // then
            assertThat(userList.size()).isEqualTo(2);

        }

        @Test
        @DisplayName("비어 있는 경우 비어있는 리스트 반환")
        void finAllByUseridTest2() {


            // when
            List<User> userList = userRepository.findAllByName("hoho");
            assertThat(userList).isInstanceOf(List.class);
            // then
            assertThat(userList.size()).isEqualTo(0);

        }

    }

}
