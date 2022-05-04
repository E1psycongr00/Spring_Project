package com.example.spring_project.unit.util.mapper;


import com.example.spring_project.dto.UserDto;
import com.example.spring_project.entity.User;
import com.example.spring_project.util.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest {

    @Nested     // jupiter.api
    @DisplayName("mapper 기능 테스트")
    class functional {

        @Test
        @DisplayName("user -> dto")
        void mapperFunctionTest1() {
            UserDto userDto = UserDto.builder()
                    .userid("hello")
                    .name("hihi")
                    .build();

            User user = UserMapper.mapper(userDto);
            assertThat(user.getName()).isEqualTo(userDto.getName());
            assertThat(user.getUserid()).isEqualTo(userDto.getUserid());
        }

        @Test
        @DisplayName("dto -> user")
        void mapperFunctionTest2() {
            User user = User.builder()
                    .userid("1")
                    .msrl(10)
                    .name("hihi")
                    .build();

            UserDto userDto = UserMapper.mapper(user);
            assertThat(user.getName()).isEqualTo(userDto.getName());
            assertThat(user.getUserid()).isEqualTo(userDto.getUserid());
        }
    }

}
