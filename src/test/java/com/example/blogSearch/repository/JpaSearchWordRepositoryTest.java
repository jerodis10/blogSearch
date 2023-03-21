package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JpaSearchWordRepositoryTest {

    @Autowired
    private SearchWordRepository searchWordRepository;

//    @Autowired
//    private EntityManager em;

//    @DisplayName("[Repository] 검색어 counting test")
//    @Test
//    void findTop10Test() {
//        // given when
//        List<SearchWord> searchWordList = searchWordRepository.findPopularBySorting();
//
//        // then
//        assertThat(searchWordList.size()).isEqualTo(10);
//    }

    @DisplayName("[Repository] 검색어 sorting test")
    @Test
    void searchWordSortingTest() {
        // given
        List<SearchWord> searchWordList = searchWordRepository.findPopularBySorting();

        // when
        List<Integer> countList = new ArrayList<>();
        List<Integer> sortedCountList = new ArrayList<>();
        for (SearchWord searchWord : searchWordList) {
            countList.add(searchWord.getSearchCount());
            sortedCountList.add(searchWord.getSearchCount());
        }

        Collections.sort(sortedCountList, Comparator.reverseOrder());

        // then
        assertThat(countList).isEqualTo(sortedCountList);
    }

    @DisplayName("[Repository] 인기 검색어 save test")
    @Test
    void saveTest() {
        // given
        String keyword = "word";

        // when
        SearchWord searchWord = searchWordRepository.save(keyword);

        // then
        assertThat(searchWord).isNotNull();
    }


}