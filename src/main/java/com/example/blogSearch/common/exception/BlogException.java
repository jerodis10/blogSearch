package com.example.blogSearch.common.exception;

import com.example.blogSearch.exception.KakaoException;
import lombok.Getter;

@Getter
public class BlogException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public BlogException(final String errorMessage, final String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BlogException(final String errorMessage, final String errorCode, final String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BlogException(BlogExceptionStatus blogExceptionStatus, final String detailMessage) {
        super(detailMessage);
        this.errorCode = blogExceptionStatus.getStatusCode();
        this.errorMessage = blogExceptionStatus.getMessage();
    }

}
