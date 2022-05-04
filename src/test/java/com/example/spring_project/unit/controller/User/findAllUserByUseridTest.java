package com.example.spring_project.unit.controller.User;


import com.example.spring_project.controller.UserController;
import com.example.spring_project.dto.UserDto;
import com.example.spring_project.dto.response.CustomHttpStatus;
import com.example.spring_project.dto.response.Response;
import com.example.spring_project.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class findAllUserByUseridTest {
    @MockBean
    UserService userService;

    @Autowired
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("findAllUserByUserid 테스트")
    void successTest() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .userid("hello")
                .name("world")
                .build();
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);


        Response<List<UserDto>> userListResponse = Response.<List<UserDto>>builder()
                .code(CustomHttpStatus.OK)
                        .data(userDtoList)
                                .build();
        // when
        Mockito.doReturn(userListResponse).when(userService).findByUserid("hello");

        // then
        mockMvc.perform(get("/v1/users/hello"))
                .andExpect(status().isOk())
                .andDo(print());

    }
}
