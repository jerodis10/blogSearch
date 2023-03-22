package com.example.blogSearch.repository;

import com.example.blogSearch.model.PopularWord;
import com.example.blogSearch.model.SearchWord;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface SearchWordRepository {

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    SearchWord findByKeyword(String keyword);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<SearchWord> findAll();

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<PopularWord> findAllPopular();

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<SearchWord> findPopular();

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    SearchWord save(String keyword);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    PopularWord popularSave(SearchWord searchWord);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void delete(PopularWord popularWord);

}
