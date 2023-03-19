package com.example.blogSearch.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * totalCount: 검색어에 검색된 문서 수
 * pageableCount: total_count 중 노출 가능 문서 수, 최대 45
 * isEnd: 현재 페이지가 마지막 페이지인지 여부
 * 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Meta {
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;

    @Builder
    public Meta(Integer totalCount, Integer pageableCount, Boolean isEnd) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }
}
