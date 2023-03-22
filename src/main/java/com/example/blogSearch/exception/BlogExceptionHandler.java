package com.example.blogSearch.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class BlogExceptionHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == CLIENT_ERROR
                || httpResponse.getStatusCode().series() == SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
//        int statusCode = httpResponse.getStatusCode().value();
        String httpResponseName = httpResponse.getStatusCode().name();
//        ErrorCode errorCode =
        throw new BlogException(BlogErrorCode.valueOf(httpResponseName));
//        throw new BlogException(BlogErrorCode.valueOf(String.valueOf(httpResponse.getStatusCode())));
//        throw new BlogException(statusCode, BlogExceptionStatus.getCode(statusCode), BlogExceptionStatus.getMessage(statusCode));
    }
}
