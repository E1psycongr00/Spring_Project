package com.example.spring_project.controller;

import com.example.spring_project.entity.User;
import com.example.spring_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// class 상단에 선언하면 내부의 final 객체에 대하여 Constructor Injection을 수행한다.
// 이 어노테이션을 사용하지 않는다면 내부에서 @Autowired를 사용하면 됨.
@RequiredArgsConstructor
@RestController         // 결과값은 JSON으로 출력 @ResponseBody를 사용 할 필요가 없음.
@RequestMapping(value = "/v1") // api resource를 버전별로 관리하기 위해서 /v1 이 모두 적용되게 처리함.
public class UserController {
    private final UserRepository userRepository; // @RequiredArgsConstructor 에 의해 자동으로 주입됨.

    @GetMapping(value = "/user")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/user")
    public User Save(@RequestBody User user) {
        return userRepository.save(user);
    }


}
