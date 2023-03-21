package com.example.blogSearch.service;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.repository.SearchWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogApiService {

    private final RestTemplateApiCaller restTemplateApiCaller;
    private final SearchWordRepository searchWordRepository;

    @Transactional
    public List<BlogResponse> blogSearch(String query, String sort, int page, int size) {
        searchWordSave(query);
        if(page > 0) return findPlaces(query, sort, page, size);
        else return findAllPlaces(query, sort, page, size);
    }

    public List<BlogResponse> findPlaces(String query, String sort, int page, int size) {
        List<BlogResponse> result = new ArrayList<>();
        result.add(restTemplateApiCaller.findBlogByKeyword(query, sort, page, size));
        return result;
    }

    public List<BlogResponse> findAllPlaces(String query, String sort, int page, int size) {
        List<BlogResponse> result = new ArrayList<>();
        int pageCount = 1;
        int countSum = 0;

        BlogResponse curPage = restTemplateApiCaller.findBlogByKeyword(query, sort, pageCount, size);
        if (restTemplateApiCaller.isLessOrEqualTotalCount(curPage) && countSum < restTemplateApiCaller.getMaxCount()) {
            result.add(curPage);
            countSum += size;
            BlogResponse nextPage = restTemplateApiCaller.findBlogByKeyword(query, sort, ++pageCount, size);
            while (restTemplateApiCaller.isLessOrEqualTotalCount(nextPage) && countSum < restTemplateApiCaller.getMaxCount()) {
                result.add(nextPage);
                countSum += size;
            }
        }

        return result;
    }

    public void searchWordSave(String keyword) {
        searchWordRepository.save(keyword);
    }

    @Transactional(readOnly = true)
    public List<SearchWord> searchWordTop10() {
        return searchWordRepository.findTop10();
    }

}
