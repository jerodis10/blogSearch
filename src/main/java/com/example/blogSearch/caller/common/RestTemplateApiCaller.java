package com.example.blogSearch.caller.common;


import com.example.blogSearch.common.dto.BlogResponse;

public interface RestTemplateApiCaller {

    public BlogResponse findBlogByKeyword(String query, String sort, int page, int size);

}
