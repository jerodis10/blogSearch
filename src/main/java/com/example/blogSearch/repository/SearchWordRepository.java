package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;

import java.util.List;

public interface SearchWordRepository {

    SearchWord findByKeyword(String keyword);

    List<SearchWord> findAll();

    List<SearchWord> findTop10();

    SearchWord save(String keyword);

}
