package com.example.blogSearch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BlogException extends RuntimeException {

    private final ErrorCode errorCode;

}
