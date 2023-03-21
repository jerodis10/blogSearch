package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;

public interface SearchWordSaveStrategy {

    SearchWord save(String keyword);
}
