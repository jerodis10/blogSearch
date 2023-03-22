package com.example.blogSearch.common.exception;

import com.example.blogSearch.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CommonErrorCode implements ErrorCode {

    UNEXPECTED(HttpStatus.BAD_REQUEST, "요청을 처리하지 못했습니다."),
    REQUEST_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "처리할 수 없는 요청 URI입니다."),
    WRONG_ARGUMENT(HttpStatus.BAD_REQUEST, "전달받은 매개변수가 올바르지 않습니다."),
    ALREADY_PERSIST(HttpStatus.BAD_REQUEST, "이미 등록되었습니다."),
    NOT_PERSIST(HttpStatus.BAD_REQUEST, "등록되어있지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final HttpStatus httpStatus;
    private final String message;

}
