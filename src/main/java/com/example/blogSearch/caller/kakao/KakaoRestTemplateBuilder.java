package com.example.blogSearch.caller.kakao;

import com.example.blogSearch.exception.BlogExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.time.Duration;

public class KakaoRestTemplateBuilder {
    public static RestTemplateBuilder get(KakaoProperties kakaoProperties) {
        return new RestTemplateBuilder()
            .rootUri(kakaoProperties.getBaseUrl())
            .errorHandler(new BlogExceptionHandler())
            .defaultHeader(kakaoProperties.getHeaderName(), kakaoProperties.getHeaderBaseValue() + " " + kakaoProperties.getKey())
            .setConnectTimeout(Duration.ofSeconds(10))
            .setReadTimeout(Duration.ofSeconds(10));
    }
}
