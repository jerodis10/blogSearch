package com.example.blogSearch.model;

import com.example.blogSearch.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@Entity
public class SearchWordPopular extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String keyword;

    private int searchCount;

    @Builder
    public SearchWordPopular(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public SearchWord searchWordBinding() {
        return SearchWord.builder()
                .keyword(keyword)
                .searchCount(searchCount)
                .build();
    }

    public void changeSearchCount(int searchCount) {
        this.searchCount = searchCount + 1;
    }

}
