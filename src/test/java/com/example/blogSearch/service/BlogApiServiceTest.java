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


    @DisplayName("[service] kakao rest api ȣ���� ���� ��α� �˻� test")
    @Test
    void kakaoFindPlacesTest() {
        // given
        int maxPage = 50;
        String query = "���ﳭ���";
        String sort = "accuracy";
        int page = 1;
        int size = 10;

        // when
        List<BlogResponse> blogResponseList = blogApiService.findPlaces(query, sort, page, size);

        // then
        assertThat(blogResponseList.size()).isLessThanOrEqualTo(maxPage * size);
    }

    @DisplayName("[service] naver rest api ȣ���� ���� ��α� �˻� test")
    @Test
    void naverFindPlacesTest() {
        // given
        int maxPage = 100;
        String query = "���ﳭ���";
        String sort = "sim";
        int page = 1;
        int size = 10;

        // when
        List<BlogResponse> blogResponseList = blogApiService.findPlaces(query, sort, page, size);

        // then
        assertThat(blogResponseList.size()).isLessThanOrEqualTo(maxPage * size);
    }

    @DisplayName("[service] �˻��� ���� test")
    @Test
    void searchWordSaveTest() {
        // given
        String keyword = "�˻���";

        // when
        SearchWord saveWord = searchWordRepository.save(keyword);
        SearchWord searchWord = searchWordRepository.findByKeyword(keyword);

        // then
        assertThat(saveWord).isEqualTo(searchWord);
    }

    @DisplayName("[service] �α� �˻��� ��ȸ test")
    @Test
    void searchWordTop10Test() {
        // given when
        List<SearchWord> searchWordList = searchWordRepository.findTop10();

        // then
        assertThat(searchWordList.size()).isEqualTo(10);
    }

}