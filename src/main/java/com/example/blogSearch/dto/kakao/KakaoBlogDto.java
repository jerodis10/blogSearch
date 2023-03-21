package com.example.blogSearch.dto.kakao;

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
public class KakaoBlogDto {
    private Meta meta;
    private List<Document> documents;

    public int getTotalCount() {
        return this.meta.getTotalCount();
    }

    public BlogResponse toBlogResponse(KakaoBlogDto kakaoBlogDto) {
        List<BlogDocument> blogDocuments = new ArrayList<>();

        for (Document document : kakaoBlogDto.getDocuments()) {
            BlogDocument blogDocument = BlogDocument.builder()
                    .title(document.getTitle())
                    .contents(document.getContents())
                    .url(document.getUrl())
                    .blogname(document.getBlogname())
                    .datetime(document.getDatetime())
                    .build();

            blogDocuments.add(blogDocument);
        }

        return BlogResponse.builder()
                .totalCount(kakaoBlogDto.getTotalCount())
                .documents(blogDocuments)
                .build();
    }

}
