package com.example.blogSearch.caller.naver;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.dto.naver.NaverBlogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
        return naverBlogDto.toBlogResponse(naverBlogDto);
    }

    @Override
    public Boolean isLessOrEqualTotalCount(BlogResponse blogResponse) {
        int totalCount = blogResponse.getTotalCount();
        return getMaxCount() <= totalCount;
    }

    @Override
    public Integer getMaxCount() {
        int maxPage = Integer.parseInt(naverProperties.getMaxPage());
        int maxSize = Integer.parseInt(naverProperties.getDisplay());
        return maxPage * maxSize;
    }

}
