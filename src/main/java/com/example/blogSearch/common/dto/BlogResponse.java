package com.example.blogSearch.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BlogResponse {

    private Integer totalCount;
    private List<BlogDocument> documents;

    @Builder
    public BlogResponse(Integer totalCount, List<BlogDocument> documents) {
        this.totalCount = totalCount;
        this.documents = documents;
    }
}
