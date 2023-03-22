package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
        searchWordRepository.save("word");
    }

    @DisplayName("[Repository] 검색어 조회 test")
    @Test
    void searchWordSortingTest() {
        // given
        String keyword = "word";
        SearchWord searchWord = searchWordRepository.findByKeyword(keyword);

        // when then
        assertThat(searchWord.getKeyword()).isEqualTo(keyword);
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