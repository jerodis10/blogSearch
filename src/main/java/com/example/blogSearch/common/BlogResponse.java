package com.example.blogSearch.common;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BlogResponse {

    private Integer totalCount;
    private List<BlogDocument> documents;

    @Builder
    public BlogResponse(Integer totalCount, List<BlogDocument> documents) {
        this.totalCount = totalCount;
        this.documents = documents;
    }
}
