package com.example.blogSearch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoException extends RuntimeException {

    private final int statusCode;
    private final String errorType;
    private final String message;

}
