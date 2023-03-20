package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaSearchWordRepositoryTest {

    @Autowired
    private SearchWordRepository searchWordRepository;

    @DisplayName("[Repository] �α� �˻��� ��ȸ counting test")
    @Test
    void findTop10Test() {
        // given when
        List<SearchWord> searchWordList = searchWordRepository.findTop10();

        // then
        assertThat(searchWordList.size()).isEqualTo(10);
    }

    @DisplayName("[Repository] �α� �˻��� ��ȸ sorting test")
    @Test
    void searchWordSortingTest() {
        // given
        List<SearchWord> searchWordList = searchWordRepository.findTop10();

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

    @DisplayName("[Repository] �α� �˻��� ��ȸ ranking test")
    @Test
    void searchWordRankingTest() {
        // given
        List<SearchWord> searchWordList = searchWordRepository.findTop10();
        List<SearchWord> allCountList = searchWordRepository.findAll();
        List<SearchWord> sortedCountList = new ArrayList<>();

        // when
//        Collections.sort(allCountList, Comparator.reverseOrder());
        int index = 0;
        for (SearchWord searchWord : allCountList) {
            sortedCountList.add(searchWord);
            index++;
            if(index > 10) break;
        }

        // then
        assertThat(searchWordList).isEqualTo(sortedCountList);
    }

    @DisplayName("[Repository] �˻��� save test")
    @Test
    void saveTest() {
        // given
        String keyword = "�˻���";

        // when
        searchWordRepository.save(keyword);
        SearchWord searchWord = searchWordRepository.findByKeyword(keyword);

        // then
        assertThat(searchWord).isNotNull();
    }


}