package com.example.spring_project.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Response<T> {
    @JsonProperty("status")
    protected CustomHttpStatus code;

    @JsonProperty("detail")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detail;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

}
