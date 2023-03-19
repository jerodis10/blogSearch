package com.example.blogSearch.caller.naver;

import com.example.blogSearch.exception.BlogExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.time.Duration;

public class NaverRestTemplateBuilder {
    public static RestTemplateBuilder get(NaverProperties naverProperties) {
        return new RestTemplateBuilder()
            .rootUri(naverProperties.getBaseUrl())
            .errorHandler(new BlogExceptionHandler())
            .defaultHeader(naverProperties.getHeaderId(), naverProperties.getClientId())
            .defaultHeader(naverProperties.getHeaderSecret(), naverProperties.getClientSecret())
            .setConnectTimeout(Duration.ofSeconds(10))
            .setReadTimeout(Duration.ofSeconds(10));
    }
}
