package com.example.blogSearch.controller;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.common.dto.PopularBlogResponse;
import com.example.blogSearch.service.BlogApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/blog/")
@RequiredArgsConstructor
@Slf4j
public class BlogController {

    private final RestTemplateApiCaller restTemplateApiCaller;
    private final BlogApiService blogApiService;


    /**
     * @param  = 0 이면 최대로 가져올 수 있는 블로그 전체 조회
     *           아니면 Pagination 형태로 제공
     */
    @GetMapping("/search")
    public List<BlogResponse> blogSearch(
            @RequestParam @NotNull String query,
            @RequestParam(required = false, defaultValue = "accuracy") String sort,
            @RequestParam(required = false, defaultValue = "1") @Min(value = 0, message = "페이지는 0 이상이어야 합니다.") int page,
            @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = "한 페이지에 보여질 문서는 1 이상이어야 합니다.") int size) {

        return blogApiService.blogSearch(query, sort, page, size);
    }

    /**
     * 인기 블로그 조회
     */
    @GetMapping("/keyword")
    public List<PopularBlogResponse> blogKeyword() {
        return blogApiService.searchWordPopular();
    }

}
