package com.example.blogSearch.caller.kakao;

import com.example.blogSearch.dto.kakao.KakaoBlogDto;
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
class KakaoRestTemplateApiCallerTest {

    @InjectMocks
    private KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller;
//
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private KakaoProperties kakaoProperties;

    @DisplayName("키워드에 대한 Kakao API 통신 확인")
    @Test
    void kakaoBlogCallerTest() {
        // given
        String uri = "/v2/search/blog?query=서울난곡로?&sort=accuracy&page=1&size=10";
        when(kakaoProperties.getBaseUrl()).thenReturn("https://dapi.kakao.com");
        when(kakaoProperties.getHeaderName()).thenReturn("Authorization");
        when(kakaoProperties.getHeaderBaseValue()).thenReturn("KakaoAK");
        when(kakaoProperties.getKey()).thenReturn("af2408226e91805021d1adc7a9d31b36");

        // when
        RestTemplate restTemplate = KakaoRestTemplateBuilder.get(kakaoProperties)
                .build();

        KakaoBlogDto kakaoBlogDto = restTemplate.getForObject(uri, KakaoBlogDto.class);

        // then
        assertThat(kakaoBlogDto).isNotNull();
        assertThat(kakaoBlogDto.getMeta()).isNotNull();
        assertThat(kakaoBlogDto.getDocuments().size()).isNotEqualTo(0);
    }
    
    @Test
    @DisplayName("UriComponents 생성 test")
    void UriComponentsTest() {
        // given
        when(kakaoProperties.getBlogSearchUrl()).thenReturn("/v2/search/blog");
        when(kakaoProperties.getQuery()).thenReturn("query");
        when(kakaoProperties.getSort()).thenReturn("sort");
        when(kakaoProperties.getPage()).thenReturn("page");
        when(kakaoProperties.getSize()).thenReturn("size");

        // when
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path(kakaoProperties.getBlogSearchUrl())
                .queryParam(kakaoProperties.getQuery(), "word")
                .queryParam(kakaoProperties.getSort(), "accuracy")
                .queryParam(kakaoProperties.getPage(), 1)
                .queryParam(kakaoProperties.getSize(), 10)
                .build();

        //then
        assertThat(uri.toUriString()).isEqualTo("/v2/search/blog?query=word&sort=accuracy&page=1&size=10");
    }

}