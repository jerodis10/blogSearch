package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;

import java.util.List;

public interface SearchWordRepository {

    List<SearchWord> findTop10();

    void save(String keyword);

}
