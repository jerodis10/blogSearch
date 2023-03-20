package com.example.blogSearch.caller.kakao;

import com.example.blogSearch.common.dto.BlogDocument;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.dto.kakao.KakaoBlogDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class KakaoRestTemplateApiCallerTest {

    @InjectMocks
    private KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller;

    @Mock
    private RestTemplate restTemplate;

    @DisplayName("KakaoBlogDto to BlogResponse º¯È¯ test")
    @Test
    void KakaoResponseDtoMappingTest() {
        // given
        BlogDocument blogDocument = BlogDocument.builder()
                .title("a")
                .contents("d")
                .url("d")
                .blogname("d")
                .datetime("d")
                .build();

        BlogResponse blogResponse = BlogResponse.builder()
                .totalCount(1)
                .documents(List.of(blogDocument))
                .build();

        String uri = "dsf";
        KakaoBlogDto kakaoBlogDto = restTemplate.getForObject(uri, KakaoBlogDto.class);

        // when
        BlogResponse targetBlogResponse = kakaoRestTemplateApiCaller.responseDtoMapping(kakaoBlogDto);

        // then
        assertThat(targetBlogResponse).isEqualTo(blogResponse);
    }

    @DisplayName("findBlogByKeywordTest")
    @Test
    void findBlogByKeywordTest() {
        // given


        // when


        // then

    }


}