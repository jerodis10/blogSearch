package com.example.blogSearch.service;

import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.repository.JpaSearchWordRepository;
import com.example.blogSearch.repository.SearchWordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BlogApiServiceTest {

    @InjectMocks
    private BlogApiService blogApiService;

    @Mock
    private SearchWordRepository searchWordRepository;


    @DisplayName("[service] kakao rest api 호출을 통한 블로그 검색 test")
    @Test
    void kakaoFindPlacesTest() {
        // given
        int maxPage = 50;
        String query = "서울난곡로";
        String sort = "accuracy";
        int page = 1;
        int size = 10;

        // when
        List<BlogResponse> blogResponseList = blogApiService.findPlaces(query, sort, page, size);

        // then
        assertThat(blogResponseList.size()).isLessThanOrEqualTo(maxPage * size);
    }

    @DisplayName("[service] naver rest api 호출을 통한 블로그 검색 test")
    @Test
    void naverFindPlacesTest() {
        // given
        int maxPage = 100;
        String query = "서울난곡로";
        String sort = "sim";
        int page = 1;
        int size = 10;

        // when
        List<BlogResponse> blogResponseList = blogApiService.findPlaces(query, sort, page, size);

        // then
        assertThat(blogResponseList.size()).isLessThanOrEqualTo(maxPage * size);
    }

    @DisplayName("[service] 검색어 저장 test")
    @Test
    void searchWordSaveTest() {
        // given
        String keyword = "검색어";

        // when
        SearchWord saveWord = searchWordRepository.save(keyword);
        SearchWord searchWord = searchWordRepository.findByKeyword(keyword);

        // then
        assertThat(saveWord).isEqualTo(searchWord);
    }

    @DisplayName("[service] 인기 검색어 조회 test")
    @Test
    void searchWordTop10Test() {
        // given when
        List<SearchWord> searchWordList = searchWordRepository.findTop10();

        // then
        assertThat(searchWordList.size()).isEqualTo(10);
    }

}