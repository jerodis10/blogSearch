package com.example.blogSearch.caller.naver;

import com.example.blogSearch.caller.common.RestTemplateApiCaller;
import com.example.blogSearch.common.BlogResponse;
import com.example.blogSearch.common.BlogDocument;
import com.example.blogSearch.dto.naver.Item;
import com.example.blogSearch.dto.naver.NaverBlogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class NaverRestTemplateApiCaller implements RestTemplateApiCaller {

    private final RestTemplate restTemplate;
    private final NaverProperties naverProperties;

    @Override
    public BlogResponse findBlogByKeyword(String query, String sort, int start, int display) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .path(naverProperties.getBlogSearchUrl())
                .queryParam(naverProperties.getQuery(), query)
                .queryParam(naverProperties.getDisplay(), display)
                .queryParam(naverProperties.getStart(), start)
                .queryParam(naverProperties.getSort(), sort)
                .build();

        NaverBlogDto naverBlogDto = restTemplate.getForObject(uri.toUriString(), NaverBlogDto.class);
        return responseDtoMapping(naverBlogDto);
    }

    public BlogResponse responseDtoMapping(NaverBlogDto naverBlogDto) {
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

        BlogResponse blogResponse = BlogResponse.builder()
                .totalCount(naverBlogDto.getTotal())
                .documents(documents)
                .build();

        return blogResponse;
    }



//    public KakaoBlogDto findBlogByKeyword(String query, String sort, int page, int size) {
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .path(naverProperties.getBlogSearchUrl())
//                .queryParam("query", query)
//                .queryParam("sort", sort)
//                .queryParam("page", page)
//                .queryParam("size", size)
//                .build();
//        return restTemplate.getForObject(uri.toUriString(), KakaoBlogDto.class);
//    }

//    public Boolean isLessOrEqualTotalCount(KakaoBlogDto kakaoBlogDto) {
//        int totalCount = kakaoBlogDto.getTotalCount();
//        return (kakaoProperties.getMaxDocumentCount() * kakaoProperties.getMaxPageableCount()) >= totalCount;
//    }

}
