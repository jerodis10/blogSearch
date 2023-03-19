package com.example.blogSearch.dto.naver;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * title: 블로그 글 제목
 * contents: 블로그 글 요약
 * url: 블로그 글 URL
 * blogname: 블로그의 이름
 * thumbnail: 검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음
 * datetime: 블로그 글 작성시간
 *
 * ex) {
 * "title": "서울 강남구 중학교 급식",
 * "contents": "서울 강남구에는 23개의...",
 * "url": "http://hhshyn.tistory.com/239",
 * "blogname": "출근안하는삶",
 * "thumbnail": "https://search4.kakaocdn.net/argon/130x130_85_c/JSLkHkfnCMk",
 * "datetime": "2023-03-07T12:54:01.000+09:00",
 *
 * }
 */

@Getter
@NoArgsConstructor
public class Item {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;

    @Builder
    public Item(String title, String link, String description, String bloggername, String bloggerlink, String postdate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.bloggername = bloggername;
        this.bloggerlink = bloggerlink;
        this.postdate = postdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return title.equals(item.title) && link.equals(item.link) && description.equals(item.description) && bloggername.equals(item.bloggername) && bloggerlink.equals(item.bloggerlink) && postdate.equals(item.postdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, description, bloggername, bloggerlink, postdate);
    }
}
