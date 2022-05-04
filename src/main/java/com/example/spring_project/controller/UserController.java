package com.example.spring_project.controller;

import com.example.spring_project.dto.UserDto;
import com.example.spring_project.dto.response.Response;
import com.example.spring_project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags ={"1. User"}) // UserController 최상단 타이틀 영역에 표시될 값
// class 상단에 선언하면 내부의 final 객체에 대하여 Constructor Injection을 수행한다.
// 이 어노테이션을 사용하지 않는다면 내부에서 @Autowired를 사용하면 됨.
@RequiredArgsConstructor
@RestController         // 결과값은 JSON으로 출력 @ResponseBody를 사용 할 필요가 없음.
@RequestMapping(value = "/v1") // api resource를 버전별로 관리하기 위해서 /v1 이 모두 적용되게 처리함.
public class UserController {
    private final UserService userService; // @RequiredArgsConstructor 에 의해 자동으로 주입됨.

    @ApiOperation(value = "회원조회", notes = "모든 회원을 조회한다.") // 각각의 resource에 설명 제공
    @GetMapping(value = "/users")  // 파라미터에 대한 설명을 보여주기 위해 세팅
    public Response<UserDto> findAllUser() {
        Response<UserDto> response = userService.findAll();
        return response;
    }

    @ApiOperation(value = "회원조회", notes = "이름을 통해 모든 회원을 조회한다.")
    @GetMapping(value = "/users/{name}")
    public Response<UserDto> findAllUserByUserid(@PathVariable String name) {
        Response<UserDto> response = userService.findByUserid(name);
        return response;
    }

    @ApiOperation(value="회원 입력", notes = "회원을 입력한다.")
    @PostMapping(value = "/user")
    public Response<UserDto> saveUser( @RequestBody UserDto userDto) {
        Response<UserDto> response = userService.insertUser(userDto);
        return response;
    }

    @ApiOperation(value="회원 수정", notes = "회원을 수정한다.")
    @PutMapping(value = "/user")
    public Response<UserDto> updateUser(@RequestBody UserDto userDto) {
        Response<UserDto> response = userService.updateUser(userDto);
        return response;
    }

    @ApiOperation(value="회원 삭제", notes = "회원을 삭제한다.")
    @PutMapping(value = "/user/{userid}")
    public Response<UserDto> updateUser(@PathVariable String userid) {
        Response<UserDto> response = userService.deleteUser(userid);
        return response;
    }
}
