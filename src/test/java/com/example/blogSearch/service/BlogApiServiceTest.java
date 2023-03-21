package com.example.blogSearch.service;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.repository.SearchWordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class BlogApiServiceTest {

    @InjectMocks
    private BlogApiService blogApiService;

    @Mock
    private SearchWordRepository searchWordRepository;

    @Mock
    private RestTemplateApiCaller restTemplateApiCaller;


    @DisplayName("[service] kakao rest api 호출 test")
    @Test
    void kakaoFindPlacesTest() {
        // given
        int maxPage = 50;
        String query = "서울 난곡로";
        String sort = "accuracy";
        int page = 1;
        int size = 10;

        // when
        List<BlogResponse> blogResponseList = blogApiService.findPlaces(query, sort, page, size);

        // then
        assertThat(blogResponseList.size()).isLessThanOrEqualTo(maxPage * size);
    }

    @DisplayName("[service] naver rest api 호출 test")
    @Test
    void naverFindPlacesTest() {
        // given
        int maxPage = 100;
        String query = "서울 난곡로";
        String sort = "sim";
        int page = 1;
        int size = 10;

        // when
        List<BlogResponse> blogResponseList = blogApiService.findPlaces(query, sort, page, size);

        // then
        assertThat(blogResponseList.size()).isLessThanOrEqualTo(maxPage * size);
    }

}