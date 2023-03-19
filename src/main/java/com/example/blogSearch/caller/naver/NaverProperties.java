package com.example.blogSearch.caller.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "naver")
public class NaverProperties {
    private String baseUrl;
    private String blogSearchUrl;
    private String headerId;
    private String headerSecret;
    private String clientId;
    private String clientSecret;
    private String query;
    private String display;
    private String start;
    private String sort;

}
