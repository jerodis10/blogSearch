package com.example.blogSearch.caller.kakao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KakaoPropertiesTest {

    @Autowired
    private KakaoProperties kakaoProperties;

    @DisplayName("kakaoProperties value binding test")
    @Test
    void KakaoPropertiesTest() {
        assertThat(kakaoProperties.getKey()).isNotNull();
        assertThat(kakaoProperties.getBaseUrl()).isEqualTo("https://dapi.kakao.com");
        assertThat(kakaoProperties.getBlogSearchUrl()).isEqualTo("/v2/search/blog");
        assertThat(kakaoProperties.getHeaderName()).isEqualTo("Authorization");
        assertThat(kakaoProperties.getHeaderBaseValue()).isEqualTo("KakaoAK");
        assertThat(kakaoProperties.getQuery()).isEqualTo("query");
        assertThat(kakaoProperties.getSort()).isEqualTo("sort");
        assertThat(kakaoProperties.getPage()).isEqualTo("page");
        assertThat(kakaoProperties.getSize()).isEqualTo("size");
        assertThat(kakaoProperties.getMaxPage()).isEqualTo("50");
        assertThat(kakaoProperties.getMaxSize()).isEqualTo("50");
    }

}