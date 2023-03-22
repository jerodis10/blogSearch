package com.example.blogSearch.controller;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogDocument;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.common.dto.PopularBlogResponse;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.service.BlogApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BlogController.class)
class BlogControllerTest {

    @MockBean
    private BlogApiService blogApiService;

    @MockBean
    private RestTemplateApiCaller restTemplateApiCaller;

    @Autowired
    MockMvc mockMvc;

    @DisplayName("[Controller] blog search test")
    @Test
    void blogSearchTest() throws Exception {
        // given
        BlogDocument blogDocument = BlogDocument.builder()
                .title("블로그")
                .contents("content")
                .url("url")
                .blogname("name")
                .datetime("2020")
                .build();

        List<BlogDocument> blogDocuments = List.of(blogDocument);

        BlogResponse blogResponse = BlogResponse.builder()
                .totalCount(1)
                .documents(blogDocuments)
                .build();

        List<BlogResponse> result = List.of(blogResponse);

        when(blogApiService.blogSearch("word", "accuracy", 1, 10)).thenReturn(result);

        //when then
        mockMvc.perform(get("/blog/search")
                    .param("query", "word")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
    }

    @DisplayName("[Controller] query 파라미터 없이 블로그 검색어 요청 test")
    @Test
    void InvalidBlogSearchTest() throws Exception {
        // given
        BlogDocument blogDocument = BlogDocument.builder()
                .title("블로그")
                .contents("content")
                .url("url")
                .blogname("name")
                .datetime("2020")
                .build();

        List<BlogDocument> blogDocuments = List.of(blogDocument);

        BlogResponse blogResponse = BlogResponse.builder()
                .totalCount(1)
                .documents(blogDocuments)
                .build();

        List<BlogResponse> result = List.of(blogResponse);

        when(blogApiService.blogSearch("word", "accuracy", 1, 10)).thenReturn(result);

        //when then
        mockMvc.perform(get("/blog/search")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
    }

    @DisplayName("[Controller] searchWordTop10 test")
    @Test
    void searchWordTop10() throws Exception {
        // given
        PopularBlogResponse popularBlogResponse = PopularBlogResponse.builder()
                .keyword("word")
                .searchCount(1)
                .build();

        List<PopularBlogResponse> result = List.of(popularBlogResponse);

        when(blogApiService.searchWordPopular()).thenReturn(result);

        //when then
        mockMvc.perform(get("/blog/keyword")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
//                            "[{\"searchCount\":1,\"id\":null,\"keyword\":\"word\"}]"
                            "[{\"keyword\":\"word\",\"searchCount\":1}]"
                    ))
                    .andDo(print());
    }

}