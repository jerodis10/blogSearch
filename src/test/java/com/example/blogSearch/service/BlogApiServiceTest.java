package com.example.blogSearch.service;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.repository.SearchWordRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlogApiServiceTest {

    @InjectMocks
    private BlogApiService blogApiService;

    @Mock
    private SearchWordRepository searchWordRepository;

    @Mock
    private RestTemplateApiCaller restTemplateApiCaller;

//    @BeforeEach
//    public void beforeEach() {
//        searchWordRepository.save("word");
//    }
//
//    @AfterEach
//    public void afterEach() {
//        searchWordRepository.delete();
//    }


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