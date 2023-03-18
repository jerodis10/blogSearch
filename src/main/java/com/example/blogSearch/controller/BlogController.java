package com.example.blogSearch.controller;

import com.example.blogSearch.caller.kakao.KakaoRestTemplateApiCaller;
import com.example.blogSearch.dto.KakaoBlogDto;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.service.KakaoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog/")
@RequiredArgsConstructor
public class BlogController {

    private final KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller;
    private final KakaoApiService kakaoApiService;

    @GetMapping("/search")
    public KakaoBlogDto blogSearch(@RequestParam String query,
                                   @RequestParam(required = false, defaultValue = "accuracy") String sort,
                                   @RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size) {

        kakaoApiService.searchWordSave(query);
        KakaoBlogDto str = kakaoRestTemplateApiCaller.findBlogByKeyword(query, sort, page, size);
        return str;
    }

    @GetMapping("/keyword")
    public List<SearchWord> blogKeyword() {
        List<SearchWord> list = kakaoApiService.searchWordTop10();
        return list;
    }

//    @GetMapping("/search")
//    public KakaoBlogDto blogSearch(String query, @RequestParam int page) {
//        KakaoBlogDto str = kakaoRestTemplateApiCaller.findBlogByKeyword(query, page);
//        return str;
//    }

//    @GetMapping("/search")
//    public List<KakaoBlogDto> blogSearch(@RequestParam String query, @RequestParam int page, @RequestParam int size) {
//        List<KakaoBlogDto> result = kakaoApiService.findPlaces(query, page, size);
//        return result;
//    }


//    @GetMapping("/search")
//    public String blogSearch(@RequestParam String query) {
//        Mono<String> mono = WebClient.builder().baseUrl(blogProperties.getBaseUrl())
//                .build().get()
//                .uri(uriBuilder -> uriBuilder.path("/v2/search/blog")
//                        .queryParam("query", query)
//                        .build()
//                )
//                .header("Authorization", "KakaoAK " + blogProperties.getKey())
//                .exchangeToMono(clientResponse -> {
//                    return clientResponse.bodyToMono(String.class);
//                });
//        return mono.block();
//    }
}
