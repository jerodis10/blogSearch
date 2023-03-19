package com.example.blogSearch.common;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

/**
 * title: 블로그 글 제목
 * contents: 블로그 글 요약
 * url: 블로그 글 URL
 * blogname: 블로그의 이름
 * datetime: 블로그 글 작성시간
 *
 * ex) {
 * "title": "서울 강남구 중학교 급식",
 * "contents": "서울 강남구에는 23개의...",
 * "url": "http://hhshyn.tistory.com/239",
 * "blogname": "출근안하는삶",
 * "datetime": "2023-03-07T12:54:01.000+09:00",
 *
 * }
 */

@Getter
public class BlogDocument {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String datetime;

    @Builder
    public BlogDocument(String title, String contents, String url, String blogname, String datetime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogname = blogname;
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogDocument document = (BlogDocument) o;
        return title.equals(document.title) && contents.equals(document.contents) && url.equals(document.url) && blogname.equals(document.blogname) && datetime.equals(document.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents, url, blogname, datetime);
    }

}
