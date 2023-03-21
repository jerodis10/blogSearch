package com.example.blogSearch.repository;

import com.example.blogSearch.common.config.PopularProperties;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.model.SearchWordPopular;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Primary
@Repository
@RequiredArgsConstructor
public class SearchWordSave implements SearchWordSaveStrategy {

    private final SearchWordRepository searchWordRepository;
    private final PopularProperties popularProperties;

    @Override
    public SearchWord save(String keyword) {
        SearchWord savedSearWord = searchWordRepository.save(keyword);
        int popularCount = Integer.parseInt(popularProperties.getPopularCount());

        int popularSize = 0;
        List<SearchWordPopular> SearchWordPopularList = searchWordRepository.findAllPopular();
        popularSize = SearchWordPopularList.size();

        if (popularSize < popularCount) {
            return searchWordRepository.popularSave(savedSearWord).searchWordBinding();
        } else if (popularSize == popularCount) {
            for (SearchWordPopular searchWordPopular : SearchWordPopularList) {
                if (searchWordPopular.getSearchCount() < savedSearWord.getSearchCount()) {
                    searchWordRepository.delete(searchWordPopular);
                    return searchWordRepository.popularSave(savedSearWord).searchWordBinding();
                }
            }
        }

        return savedSearWord;
    }
}
