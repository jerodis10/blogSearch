package com.example.blogSearch.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomResponse<T> {

    private Integer statusCode;
    private String errorType;
    private String message;

    public CustomResponse(final Integer statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public CustomResponse(final Integer statusCode, final String errorType, final String message) {
        this.statusCode = statusCode;
        this.errorType = errorType;
        this.message = message;
    }

    public static <Void> CustomResponse<Void> empty() {
        return new CustomResponse<>(null, null);
    }

    public static <Void> CustomResponse<Void> error(final Integer statusCode) {
        return new CustomResponse<>(statusCode, null);
    }

    public static <Void> CustomResponse<Void> error(final Integer statusCode, final String message) {
        return new CustomResponse<>(statusCode, message);
    }

    public static <Void> CustomResponse<Void> error(final Integer statusCode, final String errorType, final String message) {
        return new CustomResponse<>(statusCode, errorType, message);
    }

}
