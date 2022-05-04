package com.example.spring_project.unit.dto.response;


import com.example.spring_project.dto.UserDto;
import com.example.spring_project.dto.response.CustomHttpStatus;
import com.example.spring_project.dto.response.Response;
import com.example.spring_project.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ResponseJsonTest {

    @Autowired
    JacksonTester<Response<UserDto>> json;

    @Test
    void serializeTest() throws IOException {
        UserDto userDto = UserDto.builder()
                .userid("hello")
                .name("world")
                .build();

        Response<UserDto> response = Response.<UserDto>builder()
                        .code(CustomHttpStatus.OK)
                        .data(userDto)
                        .build();
//        {"status":{"code":200,"message":"OK : 요청이 성공적으로 수행되었습니다."},"data":{"userid":"hello","name":"world"}}
        assertThat(json.write(response)).hasJsonPathMapValue("@.status");
        assertThat(json.write(response)).extractingJsonPathMapValue("@.data")
                .containsValue("hello");

    }

//    @Test
//    void deSerializeTest() throws IOException {
//        UserDto userDto = UserDto.builder()
//                .userid("hello")
//                .name("world")
//                .build();
//
//        Response<UserDto> response = Response.<UserDto>builder()
//                .code(CustomHttpStatus.OK)
//                .data(userDto)
//                .build();
////        {"status":{"code":200,"message":"OK : 요청이 성공적으로 수행되었습니다."},"data":{"userid":"hello","name":"world"}}
//        String jsonString = "{\"code\":{\"code\":200,\"message\":\"OK : 요청이 성공적으로 수행되었습니다.\"},\"data\":{\"userid\":\"hello\",\"name\":\"world\"}}";
//        json.parse(jsonString);
//    }
}
