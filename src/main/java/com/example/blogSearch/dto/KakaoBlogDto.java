package com.example.blogSearch.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * meta: Response에 대한 Meta 데이터
 * documents: 검색한 블로그 정보
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoBlogDto {
    private Meta meta;
    private List<Document> documents;

    public int getTotalCount() {
        return this.meta.getTotalCount();
    }

    public int getPageableCount() {
        return this.meta.getPageableCount();
    }
}
