package com.example.blogSearch.dto.kakao;

import lombok.Getter;

import java.util.List;

/**
 * meta: Response에 대한 Meta 데이터
 * documents: 검색한 블로그 정보
 */

@Getter
public class KakaoBlogDto {
    private Meta meta;
    private List<Document> documents;

    public int getTotalCount() {
        return this.meta.getTotalCount();
    }

}
