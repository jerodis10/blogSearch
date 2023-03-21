package com.example.blogSearch.caller.kakao;

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
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {

    @NotNull
    private String key;

    @NotNull
    private String baseUrl;

    @NotNull
    private String blogSearchUrl;

    @NotNull
    private String headerName;

    @NotNull
    private String headerBaseValue;

    @NotNull
    private String query;

    private String sort;

    private String page;

    private String size;

    private String maxPage;

    private String maxSize;


}
