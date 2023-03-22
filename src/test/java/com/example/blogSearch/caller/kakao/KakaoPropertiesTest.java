package com.example.blogSearch.caller.kakao;

import com.example.blogSearch.caller.naver.NaverProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KakaoPropertiesTest {

    @Autowired
    private KakaoProperties kakaoProperties;

    @Autowired
    private NaverProperties naverProperties;

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

    @DisplayName("naverProperties value binding test")
    @Test
    void naverPropertiesTest() {
        assertThat(naverProperties.getClientId()).isNotNull();
        assertThat(naverProperties.getClientSecret()).isNotNull();
        assertThat(naverProperties.getBaseUrl()).isEqualTo("https://openapi.naver.com");
        assertThat(naverProperties.getBlogSearchUrl()).isEqualTo("/v1/search/blog.json");
        assertThat(naverProperties.getHeaderId()).isEqualTo("X-Naver-Client-Id");
        assertThat(naverProperties.getHeaderSecret()).isEqualTo("X-Naver-Client-Secret");
        assertThat(naverProperties.getQuery()).isEqualTo("query");
        assertThat(naverProperties.getSort()).isEqualTo("sort");
        assertThat(naverProperties.getDisplay()).isEqualTo("display");
        assertThat(naverProperties.getStart()).isEqualTo("start");
        assertThat(naverProperties.getMaxPage()).isEqualTo("100");
        assertThat(naverProperties.getMaxSize()).isEqualTo("100");
    }

}