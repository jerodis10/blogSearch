package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//@Primary
@Repository
@RequiredArgsConstructor
public class SearchWordSaveBySorting implements SearchWordSaveStrategy {

    private final SearchWordRepository searchWordRepository;

    @Override
    public SearchWord save(String keyword) {
        return searchWordRepository.save(keyword);
    }
}
