package com.example.blogSearch.caller.naver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NaverPropertiesTest {

    @Autowired
    private NaverProperties naverProperties;

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