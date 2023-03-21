package com.example.blogSearch.caller.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@ConstructorBinding
@AllArgsConstructor
@Validated
@ConfigurationProperties(prefix = "naver")
public class NaverProperties {

    @NotNull
    private String baseUrl;

    @NotNull
    private String blogSearchUrl;

    @NotNull
    private String headerId;

    @NotNull
    private String headerSecret;

    @NotNull
    private String clientId;

    @NotNull
    private String clientSecret;

    @NotNull
    private String query;

    private String display;

    private String start;

    private String sort;

    private String maxPage;

    private String maxSize;

}
