package com.example.blogSearch.dto.naver;

import lombok.Getter;

import java.util.List;

/**
 * meta: Response에 대한 Meta 데이터
 * documents: 검색한 블로그 정보
 */

@Getter
public class NaverBlogDto {
    private int total;
    private int display;
    private int start;
    private List<Item> items;

}
