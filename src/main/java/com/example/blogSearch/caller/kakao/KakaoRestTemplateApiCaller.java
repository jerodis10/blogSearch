package com.example.blogSearch.caller.kakao;

import com.example.blogSearch.dto.KakaoBlogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
//@RequiredArgsConstructor
public class KakaoRestTemplateApiCaller {

    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;

    public KakaoRestTemplateApiCaller(RestTemplate restTemplate, KakaoProperties kakaoProperties) {
        this.restTemplate = restTemplate;
        this.kakaoProperties = kakaoProperties;
    }

    public KakaoBlogDto findBlogByKeyword(String query, String sort, int page, int size) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path(kakaoProperties.getBlogSearchUrl())
                .queryParam("query", query)
                .queryParam("sort", sort)
                .queryParam("page", page)
                .queryParam("size", size)
                .build();
        return restTemplate.getForObject(uri.toUriString(), KakaoBlogDto.class);
    }

//    public Boolean isLessOrEqualTotalCount(KakaoBlogDto kakaoBlogDto) {
//        int totalCount = kakaoBlogDto.getTotalCount();
//        return (kakaoProperties.getMaxDocumentCount() * kakaoProperties.getMaxPageableCount()) >= totalCount;
//    }

}
