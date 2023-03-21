package com.example.blogSearch.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PopularBlogResponse {

    private String keyword;

    private int searchCount;

    @Builder
    public PopularBlogResponse(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }
}
