package com.example.blogSearch.model;

import com.example.blogSearch.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@Entity
public class SearchWord extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String keyword;

    private int searchCount;

//    @Version
//    private Long version;

    @Builder
    public SearchWord(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public void changeSearchCount(int searchCount) {
        this.searchCount = searchCount + 1;
    }

}
