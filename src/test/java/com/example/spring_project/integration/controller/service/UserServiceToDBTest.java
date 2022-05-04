package com.example.spring_project.integration.controller.service;

import com.example.spring_project.dto.UserDto;
import com.example.spring_project.dto.response.CustomHttpStatus;
import com.example.spring_project.dto.response.Response;
import com.example.spring_project.entity.User;
import com.example.spring_project.repository.UserRepository;
import com.example.spring_project.service.UserService;
import com.example.spring_project.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    userService 테스트
    매 테스트 시작전 하나의 값이 들어간다. 그리고 들어간 데이터를 바탕으로 CRUD를 테스트한다.
 */
@DataJpaTest
@Import({UserServiceImpl.class})
public class UserServiceToDBTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void beforeTest() {
        User user = User.builder()
                        .userid("haha")
                        .name("hoho")
                        .build();

        userRepository.save(user);
    }
    @Nested
    @DisplayName("findAll 테스트")
    class findAllTest {

        @Test
        @DisplayName("성공시 1을 반환")
        void successTest() {
            Response<List<UserDto>> response = userService.findAll();
            assertThat(response.getData().size()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("findByUserid 테스트")
    class findByUseridTest {

        @Test
        @DisplayName("데이터가 없는 경우")
        void failTest() {
            Response<UserDto> response = userService.findByUserid("hello");
            assertThat(response.getCode()).isEqualTo(CustomHttpStatus.ACCEPTED);
        }

        @Test
        @DisplayName("데이터가 있는 경우")
        void successTest() {
            Response<UserDto> response = userService.findByUserid("haha");
            assertThat(response.getCode()).isEqualTo(CustomHttpStatus.OK);
            assertThat(response.getData().getUserid()).isEqualTo("haha");
        }
    }

    @Nested
    @DisplayName("insertUser 테스트")
    class insertUserTest {

        @Test
        @DisplayName("이미 회원이 있어서 등록을 할 수 없는 경우")
        void failTest() {
            UserDto userDto = UserDto.builder()
                    .userid("haha")
                    .name("hoho")
                    .build();
            Response<UserDto> response = userService.insertUser(userDto);
            assertThat(response.getCode()).isEqualTo(CustomHttpStatus.ACCEPTED);
        }

        @Test
        @DisplayName("등록이 가능한 경우")
        void successTest() {
            UserDto userDto = UserDto.builder()
                    .userid("haha12")
                    .name("hoho")
                    .build();
            Response<UserDto> response = userService.insertUser(userDto);
            assertThat(response.getCode()).isEqualTo(CustomHttpStatus.OK);
        }
    }

    @Nested
    @DisplayName("updateUser 테스트")
    class updateUser {

        @Test
        @DisplayName("회원이 없어서 수정을 못하는 경우")
        void failTest() {
            UserDto userDto = UserDto.builder()
                    .userid("haha12")
                    .name("hoho")
                    .build();
            Response<UserDto> response = userService.updateUser(userDto);
            assertThat(response.getCode()).isEqualTo(CustomHttpStatus.ACCEPTED);
        }

        @Test
        @DisplayName("수정 가능한 경우")
        void successTest() {
            UserDto userDto = UserDto.builder()
                    .userid("haha")
                    .name("hoho")
                    .build();
            Response<UserDto> response = userService.updateUser(userDto);
            assertThat(response.getCode()).isEqualTo(CustomHttpStatus.OK);
        }
    }


    @Nested
    @DisplayName("deleteUser 테스트")
    class deleteUser {

        @Test
        @DisplayName("회원이 없어서 삭제를 못하는 경우")
        void failTest() {
            UserDto userDto = UserDto.builder()
                    .userid("haha12")
                    .name("hoho")
                    .build();
            Response<UserDto> response = userService.deleteUser("haha12");
            assertThat(response.getCode()).isEqualTo(CustomHttpStatus.ACCEPTED);
        }

        @Test
        @DisplayName("삭제 가능한 경우")
        void successTest() {
            UserDto userDto = UserDto.builder()
                    .userid("haha")
                    .name("hoho")
                    .build();
            Response<UserDto> response = userService.deleteUser("haha");
            assertThat(response.getCode()).isEqualTo(CustomHttpStatus.OK);
        }
    }
}
