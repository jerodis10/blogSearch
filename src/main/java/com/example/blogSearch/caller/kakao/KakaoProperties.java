package com.example.blogSearch.caller.kakao;

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
    private Integer maxDocumentCount;
    private Integer maxPageableCount;

}
