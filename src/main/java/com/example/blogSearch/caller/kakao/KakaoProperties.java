package com.example.blogSearch.caller.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {
    private String key;
    private String baseUrl;
    private String blogSearchUrl;
    private String headerName;
    private String headerBaseValue;
    private String query;
    private String sort;
    private String page;
    private String size;


}
