package com.example.blogSearch.caller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
//@RequiredArgsConstructor
public class KakaoRestTemplateApiCaller {

//    static final String HeaderName = "Authorization";
//    static final String HeaderBaseValue = "KakaoAK ";

    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;

    public KakaoRestTemplateApiCaller(RestTemplate restTemplate, KakaoProperties kakaoProperties) {
        this.restTemplate = restTemplate;
        this.kakaoProperties = kakaoProperties;
    }

        public String findBlogByKeyword(String query) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .fromHttpUrl(kakaoProperties.getBaseUrl() + kakaoProperties.getBlogSearchUrl())
                .queryParam("query", query)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(kakaoProperties.getHeaderName(), kakaoProperties.getHeaderBaseValue() + " " + kakaoProperties.getKey());
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(uri.toUriString(), HttpMethod.GET, entity, String.class).getBody();
    }



//    public KakaoPlaceDto findPlaceByCategory(String category, Rect rect, int page) {
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .fromHttpUrl(kakaoProperties.getBaseUrl() + kakaoProperties.getCategoryUrl())
//                .queryParam(kakaoProperties.getCategoryGroupCode(), category)
//                .queryParam(kakaoProperties.getRect(), rect.toKakaoUriFormat())
//                .build();
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Authorization", "KakaoAK "+ kakaoProperties.getKey());
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//        return restTemplate.exchange(uri.toUriString(), HttpMethod.GET, entity, KakaoPlaceDto.class).getBody();
//    }
//
//    public KakaoPlaceDto findPlaceByKeyword(String category, Rect rect, String query) {
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .fromHttpUrl(kakaoProperties.getBaseUrl() + kakaoProperties.getKeywordUrl())
//                .queryParam(kakaoProperties.getQuery(), query)
//                .queryParam(kakaoProperties.getCategoryGroupCode(), category)
//                .queryParam(kakaoProperties.getRect(), rect.toKakaoUriFormat())
//                .build();
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Authorization", "KakaoAK " + kakaoProperties.getKey());
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//        return restTemplate.exchange(uri.toUriString(), HttpMethod.GET, entity, KakaoPlaceDto.class).getBody();
//    }
//
//    public Boolean isLessOrEqualTotalCount(KakaoPlaceDto kakaoPlaceDto) {
//        int totalCount = kakaoPlaceDto.getDocuments().size();
//
//        if(totalCount < kakaoProperties.getMaxDocumentCount()) return true;
//        else if(totalCount >= kakaoProperties.getMaxDocumentCount()) return false;
//        return false;
//    }
}
