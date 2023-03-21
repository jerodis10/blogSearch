package com.example.blogSearch.dto.naver;

import com.example.blogSearch.common.dto.BlogDocument;
import com.example.blogSearch.common.dto.BlogResponse;
import lombok.Getter;

import java.util.ArrayList;
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

    public BlogResponse toBlogResponse(NaverBlogDto naverBlogDto) {
        List<BlogDocument> documents = new ArrayList<>();

        for (Item item : naverBlogDto.getItems()) {
            BlogDocument document = BlogDocument.builder()
                    .title(item.getTitle())
                    .contents(item.getDescription())
                    .url(item.getBloggerlink())
                    .blogname(item.getBloggername())
                    .datetime(item.getPostdate())
                    .build();

            documents.add(document);
        }

        return BlogResponse.builder()
                .totalCount(naverBlogDto.getTotal())
                .documents(documents)
                .build();
    }

}
