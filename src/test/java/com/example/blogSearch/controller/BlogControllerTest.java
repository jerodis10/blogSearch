package com.example.blogSearch.controller;

import com.example.blogSearch.service.BlogApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = BlogControllerTest.class)
class BlogControllerTest {

    @MockBean
    private BlogApiService blogApiService;

    @DisplayName("블로그 검색 요청 controller 테스트")
    @Test
    void blogSearch() throws Exception {
        // given

        // when

        //then
    }

    @DisplayName("인기 검색어 목록 요청 controller 테스트")
    @Test
    void blogPopularKeyword() throws Exception {
        // given

        // when

        //then
    }


}