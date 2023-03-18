package com.example.blogSearch.dto;

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
public class Document {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;

    @Builder
    public Document(String title, String contents, String url, String blogname, String thumbnail, String datetime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogname = blogname;
        this.thumbnail = thumbnail;
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return title.equals(document.title) && contents.equals(document.contents) && url.equals(document.url) && blogname.equals(document.blogname) && thumbnail.equals(document.thumbnail) && datetime.equals(document.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents, url, blogname, thumbnail, datetime);
    }

}
