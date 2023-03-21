package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.model.SearchWordPopular;

import java.util.List;

public interface SearchWordRepository {

    SearchWord findByKeyword(String keyword);

    List<SearchWord> findAll();

    List<SearchWordPopular> findAllPopular();

    List<SearchWord> findPopular();

    SearchWord save(String keyword);

    SearchWordPopular popularSave(SearchWord searchWord);

    void delete(SearchWordPopular searchWordPopular);

}
