package com.example.blogSearch.model;

import com.example.blogSearch.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
@Entity
public class PopularWord extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String keyword;

    private int searchCount;


    @Builder
    public PopularWord(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public void changeSearchCount(int searchCount) {
        this.searchCount = searchCount + 1;
    }

}
