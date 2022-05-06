package com.example.spring_project.advice;


import com.example.spring_project.dto.response.CustomHttpStatus;
import com.example.spring_project.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor // final 로 생성자 주입 방식 가능
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<Response> illegalStateErrorHandler(IllegalStateException e) {
        return ResponseEntity.status(404)
                .body(Response.builder()
                        .code(CustomHttpStatus.NOT_FOUND)
                        .detail(e.getMessage())
                        .build());
    }
}
