package com.example.spring_project.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CustomHttpStatus {

    // 클라이언트 요청 에러
    BAD_REQUEST(400, "BAD_REQUEST : 잘못된 요청입니다."),
    UNAUTHORIZED(401, "UNAUTHORIZED : 인증이 필요한 페이지입니다."),
    FORBIDDEN(402, "FORBIDDEN : 접근권한이 없습니다."),
    NOT_FOUND(404, "NOT_FOUND : 요청한 페이지를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED : 허용되지 않은 HTTP 방식입니다."),
    REQUEST_TIMEOUT(408, "REQUEST_TIMEOUT : 요청 시간이 초과되었습니다."),

    // 서버 에러
    INTERVAL_SERVER_ERROR(500, "INTERVAL_SERVER_ERROR : 요청 처리 과정에서 내부 서버에 오류가 발생했습니다."),
    NOT_IMPLEMENTED(501, "NOT_IMPLEMENTED : 요청을 웹 서버에서 처리 할 수 없습니다. 지원하지 않은 방식입니다."),
    BAD_GATEWAY(502, "BAD_GATEWAY : 게이트웨이 오류입니다"),

    // SuccessCode
    /**
     *  code: 200, message: "OK : 요청이 성공적으로 수행되었습니다."
     */
    OK(200, "OK : 요청이 성공적으로 수행되었습니다."),
    CREATED(201, "CREATED : 요청이 성공적으로 수행되고 새로운 리소스가 생성되었습니다."),
    ACCEPTED(202, "ACCEPTED : 요청이 성공적으로 수행되었지만 응답 할 수 없습니다.")
    ;

    private final int code;
    private final String message;
}
