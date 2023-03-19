package com.example.blogSearch.exception;

import com.example.blogSearch.dto.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(KakaoException.class)
    public CustomResponse<Void> handleKakaoException(KakaoException e) {
        String message = String.format("KakaoException : %s", e.getMessage());
        log.error(message, e);
        return CustomResponse.error(e.getStatusCode(), e.getErrorType(), e.getMessage());
    }

//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(KakaoException.class)
//    public CustomResponse<Void> handleException(KakaoException e) {
//        String message = String.format("UNAUTHORIZED Exception : %s", e.getMessage());
//        log.error(message, e);
//        return CustomResponse.error(e.getStatusCode(), e.getErrorType(), e.getMessage());
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(Exception.class)
//    public CustomResponse<Void> handleException(Exception e) {
//        String message = String.format("Unexpected Exception : %s", e.getMessage());
//        log.error(message, e);
//        return CustomResponse.error(KakaoExceptionStatus.UNEXPECTED.getCode(), CommonExceptionStatus.UNEXPECTED.getMessage());
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public CustomResponse<Void> handleIllegalArgumentException(IllegalArgumentException e) {
//        String message = String.format("Wrong Argument Exception : %s", e.getMessage());
//        log.error(message, e);
//        return CustomResponse.error(KakaoExceptionStatus.WRONG_ARGUMENT.getCode(), CommonExceptionStatus.WRONG_ARGUMENT.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(KakaoException.class)
//    public CustomResponse<Void> handleAdminException(KakaoException e) {
//        log.info("Admin Exception : {}", e.getMessage());
//        return CustomResponse.error(e.getErrorCode(), e.getErrorMessage());
//    }
//
//    @ExceptionHandler(InstagramSchedulerException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public CustomResponse<Void> handleInstagramSchedulerException(InstagramSchedulerException e) {
//        log.info("InstagramScheduler Exception : {}", e.getMessage());
//        return CustomResponse.error(e.getErrorCode(), e.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(KakaoSchedulerException.class)
//    public CustomResponse<Void> handleKakaoScheduleException(KakaoSchedulerException e) {
//        log.info("KakaoSchedulerException : {}", e.getMessage());
//        return CustomResponse.error(e.getErrorCode(), e.getErrorMessage());
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public CustomResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.info("MethodArgumentNotValidException : {}", e.getMessage());
//        return CustomResponse.error(KakaoExceptionStatus.WRONG_ARGUMENT.getCode(), KakaoExceptionStatus.WRONG_ARGUMENT.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public CustomResponse<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//        log.info("HttpRequestMethodNotSupportedException : {}", e.getMessage());
//        return CustomResponse.error(KakaoExceptionStatus.REQUEST_NOT_ALLOWED.getCode(), KakaoExceptionStatus.REQUEST_NOT_ALLOWED.getMessage());
//    }
}
