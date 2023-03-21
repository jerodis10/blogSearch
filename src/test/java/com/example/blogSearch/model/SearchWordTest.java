package com.example.blogSearch.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SearchWordTest {

    @DisplayName("[domain] searchWord 생성 test")
    @Test
    void SearchWordBuilderTest() {
        // given
        String keyword = "word";
        int searchCount = 2;

        // when
        SearchWord searchWord = SearchWord.builder()
                .keyword(keyword)
                .searchCount(searchCount)
                .build();

        // then
        assertThat(searchWord.getKeyword()).isEqualTo(keyword);
        assertThat(searchWord.getSearchCount()).isEqualTo(searchCount);
    }

    @DisplayName("[domain] searchWord searchCount update test")
    @Test
    void changeSearchCountTest() {
        // given
        int count = 1;
        SearchWord searchWord = SearchWord.builder()
                .keyword("word")
                .searchCount(count)
                .build();

        // when
        searchWord.changeSearchCount(count);

        // then
        assertThat(searchWord.getSearchCount()).isEqualTo(count + 1);
    }

}