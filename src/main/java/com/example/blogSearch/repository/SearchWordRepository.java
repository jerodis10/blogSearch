package com.example.blogSearch.repository;

import com.example.blogSearch.model.PopularWord;
import com.example.blogSearch.model.SearchWord;

import java.util.List;

public interface SearchWordRepository {

    SearchWord findByKeyword(String keyword);

    List<SearchWord> findAll();

    List<PopularWord> findAllPopular();

    List<SearchWord> findPopular();

    SearchWord save(String keyword);

    PopularWord popularSave(SearchWord searchWord);

    void delete(PopularWord popularWord);

}
