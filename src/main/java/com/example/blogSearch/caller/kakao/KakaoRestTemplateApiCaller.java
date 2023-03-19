package com.example.blogSearch.caller.kakao;

import com.example.blogSearch.caller.common.RestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.common.dto.BlogDocument;
import com.example.blogSearch.dto.kakao.Document;
import com.example.blogSearch.dto.kakao.KakaoBlogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class KakaoRestTemplateApiCaller implements RestTemplateApiCaller {

    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;

    @Override
    public BlogResponse findBlogByKeyword(String query, String sort, int page, int size) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path(kakaoProperties.getBlogSearchUrl())
                .queryParam(kakaoProperties.getQuery(), query)
                .queryParam(kakaoProperties.getSort(), sort)
                .queryParam(kakaoProperties.getPage(), page)
                .queryParam(kakaoProperties.getSize(), size)
                .build();

        KakaoBlogDto kakaoBlogDto = restTemplate.getForObject(uri.toUriString(), KakaoBlogDto.class);
        return responseDtoMapping(kakaoBlogDto);
    }


    public BlogResponse responseDtoMapping(KakaoBlogDto kakaoBlogDto) {
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



//    public Boolean isLessOrEqualTotalCount(KakaoBlogDto kakaoBlogDto) {
//        int totalCount = kakaoBlogDto.getTotalCount();
//        return (kakaoProperties.getMaxDocumentCount() * kakaoProperties.getMaxPageableCount()) >= totalCount;
//    }

}
