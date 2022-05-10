package com.example.spring_project.service;


import com.example.spring_project.dto.UserDto;
import com.example.spring_project.dto.response.CustomHttpStatus;
import com.example.spring_project.dto.response.Response;
import com.example.spring_project.entity.User;
import com.example.spring_project.repository.UserRepository;
import com.example.spring_project.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
@RequiredArgsConstructor  // private final을 이용하면 자동으로 생성자 주입
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public Response findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = userList.stream()
                .map((user) -> UserMapper.mapper(user))
                .collect(Collectors.toList());

        return Response.<List<UserDto>>builder()
                    .code(CustomHttpStatus.OK)
                    .data(userDtoList)
                    .build();
    }

    @Override
    public Response findByUserid(String userid) {
        User user = userRepository.findByUserid(userid)
                .orElseThrow(IllegalStateException::new);
        UserDto userDto = UserMapper.mapper(user);

        return Response.<UserDto>builder()
                .code(CustomHttpStatus.OK)
                .data(userDto)
                .build();
    }

    @Override
    public Response insertUser(UserDto userDto) {
        User user = userRepository.findByUserid(userDto.getUserid())
                .orElse(null);
        if (user != null)
            throw(new IllegalStateException(
                    String.format("%s 회원이 이미 존재합니다. 요청을 받았지만 수행할 수 없습니다.",userDto.getUserid())));


        User user1 = userRepository.save(UserMapper.mapper(userDto));
        return Response.<UserDto>builder()
                .code(CustomHttpStatus.OK)
                .detail(String.format("%s 회원 저장 성공", user1.getUserid()))
                .build();
    }

    @Override
    public Response updateUser(UserDto userDto) {
        User user = userRepository.findByUserid(userDto.getUserid())
                .orElseThrow(()->new IllegalStateException(
                        String.format("%s 회원이 존재하지 않습니다. 요청을 받았지만 수행할 수 없습니다.", userDto.getUserid())));
        user.setName(userDto.getName());
        userRepository.save(user);
        return Response.<UserDto>builder()
                .code(CustomHttpStatus.OK)
                .detail(String.format("%s 회원 저장 성공", userDto.getUserid()))
                .build();
    }

    @Override
    public Response deleteUser(String userid) {
        Integer x = userRepository.deleteByUserid(userid);
        if (x == 0) {
            throw new IllegalStateException("회원이 존재하지 않아 삭제를 수행 할 수 없습니다.");
        }
        return Response.<UserDto>builder()
                .code(CustomHttpStatus.OK)
                .detail("삭제 성공")
                .build();
    }
}
