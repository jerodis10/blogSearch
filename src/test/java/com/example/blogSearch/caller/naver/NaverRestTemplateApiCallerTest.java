package com.example.blogSearch.caller.naver;

import com.example.blogSearch.dto.naver.NaverBlogDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NaverRestTemplateApiCallerTest {

    @InjectMocks
    private NaverRestTemplateApiCaller naverRestTemplateApiCaller;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private NaverProperties naverProperties;

    @DisplayName("키워드에 대한 Naver API 통신 확인")
    @Test
    void NaverBlogCallerTest() {
        // given
        String uri = "/v1/search/blog.json?query=서울난곡로&sort=sim&start=1&display=10";
        when(naverProperties.getBaseUrl()).thenReturn("https://openapi.naver.com");
        when(naverProperties.getHeaderId()).thenReturn("X-Naver-Client-Id");
        when(naverProperties.getHeaderSecret()).thenReturn("X-Naver-Client-Secret");
        when(naverProperties.getClientId()).thenReturn("HsYlQXCeP6he8vYuwTn5");
        when(naverProperties.getClientSecret()).thenReturn("DaPnZXoJlM");

        // when
        RestTemplate restTemplate = NaverRestTemplateBuilder.get(naverProperties)
                .build();

        NaverBlogDto NaverBlogDto = restTemplate.getForObject(uri, NaverBlogDto.class);

        // then
        assertThat(NaverBlogDto).isNotNull();
        assertThat(NaverBlogDto.getTotal()).isGreaterThan(0);
        assertThat(NaverBlogDto.getItems().size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("UriComponents 생성 test")
    void UriComponentsTest() {
        // given
        when(naverProperties.getBlogSearchUrl()).thenReturn("/v1/search/blog.json");
        when(naverProperties.getQuery()).thenReturn("query");
        when(naverProperties.getSort()).thenReturn("sort");
        when(naverProperties.getStart()).thenReturn("page");
        when(naverProperties.getDisplay()).thenReturn("size");

        // when
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path(naverProperties.getBlogSearchUrl())
                .queryParam(naverProperties.getQuery(), "word")
                .queryParam(naverProperties.getSort(), "sim")
                .queryParam(naverProperties.getStart(), 1)
                .queryParam(naverProperties.getDisplay(), 10)
                .build();

        //then
        assertThat(uri.toUriString()).isEqualTo("/v1/search/blog.json?query=word&sort=sim&page=1&size=10");
    }

}