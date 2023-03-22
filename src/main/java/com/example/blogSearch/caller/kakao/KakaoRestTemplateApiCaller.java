package com.example.blogSearch.caller.kakao;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.common.dto.BlogDocument;
import com.example.blogSearch.dto.kakao.Document;
import com.example.blogSearch.dto.kakao.KakaoBlogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class KakaoRestTemplateApiCaller implements RestTemplateApiCaller {

    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;

    @Override
    public BlogResponse findBlogByKeyword(String query, String sort, int page, int size) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path(kakaoProperties.getBlogSearchUrl())
//                .queryParam(kakaoProperties.getQuery(), query)
                .queryParam(kakaoProperties.getSort(), sort)
                .queryParam(kakaoProperties.getPage(), page)
                .queryParam(kakaoProperties.getSize(), size)
                .build();

        KakaoBlogDto kakaoBlogDto = restTemplate.getForObject(uri.toUriString(), KakaoBlogDto.class);
        return kakaoBlogDto.toBlogResponse(kakaoBlogDto);
    }

    @Override
    public Boolean isLessOrEqualTotalCount(BlogResponse blogResponse) {
        int totalCount = blogResponse.getTotalCount();
        return getMaxCount() <= totalCount;
    }

    @Override
    public Integer getMaxCount() {
        int maxPage = Integer.parseInt(kakaoProperties.getMaxPage());
        int maxSize = Integer.parseInt(kakaoProperties.getMaxSize());
        return maxPage * maxSize;
    }

}
