package com.example.blogSearch.common.exception;

import lombok.Getter;

@Getter
public enum BlogExceptionStatus {
    UNEXPECTED("COMMON_0000", "요청을 처리하지 못했습니다."),
    REQUEST_NOT_ALLOWED("COMMON_0100", "처리할 수 없는 요청 URI입니다."),
    WRONG_ARGUMENT("COMMON_1000", "전달받은 매개변수가 올바르지 않습니다."),
    ALREADY_PERSIST("COMMON_2000", "이미 등록되었습니다."),
    NOT_PERSIST("COMMON_2100", "등록되어있지 않습니다.");

    private final String statusCode;
    private final String message;

    BlogExceptionStatus(final String statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
