package com.example.spring_project.total;


import com.example.spring_project.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc // mockmvc Autowired 하기 위한 어노테이션
public class ControllerToDatabaseTest {

    @Autowired
    ObjectMapper objectMapper;   // post test 에서 body 에 Json 형태로 전달하기 위해 필요함
    @Autowired
    MockMvc mockMvc;             // MVC test

    @Test
    public void userTest() throws Exception {

        //given
        User user1 = User.builder()
                .uid("marrin1101@naver.com")
                .name("임현규")
                .build();
        User user2 = User.builder()
                .uid("dong@naver.com")
                .name("임동규")
                .build();

        // post 테스트
        // when
        mockMvc.perform(post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)              // json 타입으로 보낼 것을 명시
                .content(objectMapper.writeValueAsString(user1))       // user 클래스를 Json 으로 변환
        )
        // then
                .andExpect(status().is(200))
                .andDo(print());
        mockMvc.perform(post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)              // json 타입으로 보낼 것을 명시
                        .content(objectMapper.writeValueAsString(user2))       // user 클래스를 Json 으로 변환
                )
                // then
                .andExpect(status().is(200))
                .andDo(print());

        mockMvc.perform(get("/v1/users")
        )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
