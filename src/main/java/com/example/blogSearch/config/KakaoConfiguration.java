package com.example.blogSearch.config;

import com.example.blogSearch.caller.KakaoProperties;
import com.example.blogSearch.caller.KakaoRestTemplateApiCaller;
import com.example.blogSearch.caller.KakaoRestTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KakaoConfiguration {

    @Autowired
    KakaoProperties kakaoProperties;

    @Bean
    public KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller() {
        RestTemplate restTemplate = KakaoRestTemplateBuilder.get(kakaoProperties)
                .build();
        return new KakaoRestTemplateApiCaller(restTemplate, kakaoProperties);
    }
}
