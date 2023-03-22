//package com.example.blogSearch.common.exception;
//
//import lombok.Getter;
//
//@Getter
//public class CommonException extends RuntimeException {
//    private final String errorCode;
//    private final String errorMessage;
//
//    public CommonException(final String errorMessage, final String errorCode) {
//        super(errorMessage);
//        this.errorCode = errorCode;
//        this.errorMessage = errorMessage;
//    }
//
//    public CommonException(final String errorMessage, final String errorCode, final String detailMessage) {
//        super(detailMessage);
//        this.errorCode = errorCode;
//        this.errorMessage = errorMessage;
//    }
//
//    public CommonException(CommonErrorCode commonErrorCode, final String detailMessage) {
//        super(detailMessage);
//        this.errorCode = commonErrorCode.getStatusCode();
//        this.errorMessage = commonErrorCode.getMessage();
//    }
//
//}
