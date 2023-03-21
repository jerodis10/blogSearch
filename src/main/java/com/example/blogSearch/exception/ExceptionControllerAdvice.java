package com.example.blogSearch.exception;

import com.example.blogSearch.common.exception.CommonExceptionStatus;
import com.example.blogSearch.dto.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BlogException.class)
    public CustomResponse<Void> handleKakaoException(BlogException e) {
        String message = String.format("KakaoException : %s", e.getMessage());
        log.error(message, e);
        return CustomResponse.error(e.getStatusCode(), e.getErrorType(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public CustomResponse<Void> handleException(Exception e) {
        String message = String.format("Unexpected Exception : %s", e.getMessage());
        log.error(message, e);
        return CustomResponse.error(Integer.parseInt(CommonExceptionStatus.UNEXPECTED.getStatusCode()),
                                    CommonExceptionStatus.UNEXPECTED.name(),
                                    CommonExceptionStatus.UNEXPECTED.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public CustomResponse<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        String message = String.format("Wrong Argument Exception : %s", e.getMessage());
        log.error(message, e);
        return CustomResponse.error(Integer.parseInt(CommonExceptionStatus.WRONG_ARGUMENT.getStatusCode()),
                                    CommonExceptionStatus.WRONG_ARGUMENT.name(),
                                    CommonExceptionStatus.WRONG_ARGUMENT.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage());
        return CustomResponse.error(Integer.parseInt(CommonExceptionStatus.WRONG_ARGUMENT.getStatusCode()),
                                    CommonExceptionStatus.WRONG_ARGUMENT.name(),
                                    CommonExceptionStatus.WRONG_ARGUMENT.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CustomResponse<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage());
        return CustomResponse.error(Integer.parseInt(CommonExceptionStatus.REQUEST_NOT_ALLOWED.getStatusCode()),
                                    CommonExceptionStatus.REQUEST_NOT_ALLOWED.name(),
                                    CommonExceptionStatus.REQUEST_NOT_ALLOWED.getMessage());
    }


}
