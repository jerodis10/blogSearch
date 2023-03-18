package com.example.blogSearch.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KakaoException extends RuntimeException {
    private final int statusCode;
    private final String message;


}
