package com.example.blogSearch.caller.kakao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "security")
@Component
public class KakaoSecurityProperties {
    private String key;
}
