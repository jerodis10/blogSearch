package com.example.blogSearch.caller;


import com.example.blogSearch.common.dto.BlogResponse;

public interface RestTemplateApiCaller {

    public BlogResponse findBlogByKeyword(String query, String sort, int page, int size);

    public Boolean isLessOrEqualTotalCount(BlogResponse blogResponse);

    public Integer getMaxCount();

}
