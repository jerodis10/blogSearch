package com.example.blogSearch.service;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.caller.naver.NaverRestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.common.dto.PopularBlogResponse;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.repository.SearchWordRepository;
import com.example.blogSearch.repository.SearchWordSaveStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogApiService {

    private final RestTemplateApiCaller restTemplateApiCaller;
    private final NaverRestTemplateApiCaller naverRestTemplateApiCaller;
    private final SearchWordRepository searchWordRepository;
    private final SearchWordSaveStrategy searchWordSaveStrategy;


    @Transactional
    public List<BlogResponse> blogSearch(String query, String sort, int page, int size) {
        searchWordSave(query);
        if(page > 0) return findPlaces(query, sort, page, size);
        else return findAllPlaces(query, sort, page, size);
    }

    @Transactional
    public List<BlogResponse> naverBlogSearch(String query, String sort, int page, int size) {
        searchWordSave(query);

        String naverSort = sortMapping(sort);
        if(page > 0) return findPlacesByNaver(query, naverSort, page, size);
        else return findAllPlacesByNaver(query, naverSort, page, size);
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
        searchWordSaveStrategy.save(keyword);
    }

    @Transactional(readOnly = true)
    public List<PopularBlogResponse> searchWordPopular() {
        List<PopularBlogResponse> result = new ArrayList<>();
        List<SearchWord> searchWordList = searchWordRepository.findPopular();
        for (SearchWord searchWord : searchWordList) {
            PopularBlogResponse popularBlogResponse = PopularBlogResponse.builder()
                    .keyword(searchWord.getKeyword())
                    .searchCount(searchWord.getSearchCount())
                    .build();

            result.add(popularBlogResponse);
        }

        return result;
    }

    public List<BlogResponse> findPlacesByNaver(String query, String sort, int page, int size) {
        List<BlogResponse> result = new ArrayList<>();
        result.add(naverRestTemplateApiCaller.findBlogByKeyword(query, sort, page, size));
        return result;
    }

    public List<BlogResponse> findAllPlacesByNaver(String query, String sort, int page, int size) {
        List<BlogResponse> result = new ArrayList<>();
        int pageCount = 1;
        int countSum = 0;

        BlogResponse curPage = naverRestTemplateApiCaller.findBlogByKeyword(query, sort, pageCount, size);
        if (naverRestTemplateApiCaller.isLessOrEqualTotalCount(curPage) && countSum < naverRestTemplateApiCaller.getMaxCount()) {
            result.add(curPage);
            countSum += size;
            BlogResponse nextPage = naverRestTemplateApiCaller.findBlogByKeyword(query, sort, ++pageCount, size);
            while (naverRestTemplateApiCaller.isLessOrEqualTotalCount(nextPage) && countSum < naverRestTemplateApiCaller.getMaxCount()) {
                result.add(nextPage);
                countSum += size;
            }
        }

        return result;
    }

    private String sortMapping(String sort) {
        if(sort.equals("accuracy")) return "sim";
        else return "date";
    }

}
