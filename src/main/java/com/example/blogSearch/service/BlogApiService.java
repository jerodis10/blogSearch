package com.example.blogSearch.service;

import com.example.blogSearch.caller.common.RestTemplateApiCaller;
import com.example.blogSearch.dto.kakao.KakaoBlogDto;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.repository.SearchWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogApiService {

    private final RestTemplateApiCaller restTemplateApiCaller;
    private final SearchWordRepository searchWordRepository;

    public List<KakaoBlogDto> findPlaces(String query, String sort, int page, int size) {
//        List<KakaoBlogDto> result = new ArrayList<>();
//        int pageCount = page;
//        KakaoBlogDto curPage = restTemplateApiCaller.findBlogByKeyword(query, sort, page, size);
//
//
//        if(curPage != null) {
//            result.add(curPage);
//            while(!curPage.getMeta().getIsEnd()) {
//                KakaoBlogDto nextPage = restTemplateApiCaller.findBlogByKeyword(query, sort, ++pageCount, size);
//                if (nextPage != null) {
//                    result.add(nextPage);
//                    if(nextPage.getMeta().getIsEnd()) break;
//                }
//            }
//        }
//
//        return result;
        return null;
    }

    @Transactional
    public void searchWordSave(String keyword) {
        searchWordRepository.save(keyword);
    }

    @Transactional(readOnly = true)
    public List<SearchWord> searchWordTop10() {
        return searchWordRepository.findTop10();
    }

}
