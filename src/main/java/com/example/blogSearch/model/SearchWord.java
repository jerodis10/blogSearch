package com.example.blogSearch.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SearchWord {

    @Id
    @GeneratedValue
    private Long id;

    private String keyword;

    private int searchCount;

    @Builder
    public SearchWord(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public void changeSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

}
