package com.example.blogSearch.config;

import com.example.blogSearch.caller.kakao.KakaoProperties;
import com.example.blogSearch.caller.kakao.KakaoRestTemplateApiCaller;
import com.example.blogSearch.caller.kakao.KakaoRestTemplateBuilder;
import com.example.blogSearch.caller.naver.NaverProperties;
import com.example.blogSearch.caller.naver.NaverRestTemplateApiCaller;
import com.example.blogSearch.caller.naver.NaverRestTemplateBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class BlogConfiguration {

    private final KakaoProperties kakaoProperties;
    private final NaverProperties naverProperties;


    @Bean
    public KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller() {
        RestTemplate restTemplate = KakaoRestTemplateBuilder.get(kakaoProperties)
                .build();
        return new KakaoRestTemplateApiCaller(restTemplate, kakaoProperties);
    }


    @Primary
    @Bean
    public NaverRestTemplateApiCaller naverRestTemplateApiCaller() {
        RestTemplate restTemplate = NaverRestTemplateBuilder.get(naverProperties)
                .build();
        return new NaverRestTemplateApiCaller(restTemplate, naverProperties);
    }
}
