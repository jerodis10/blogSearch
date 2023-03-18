package com.example.blogSearch.caller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@ConstructorBinding
@Getter
@Setter
@ConfigurationProperties(prefix = "kakao")
@Component
public class KakaoProperties {
    private String key;
    private String baseUrl;
    private String blogSearchUrl;
    private String headerName;
    private String headerBaseValue;
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;

}
