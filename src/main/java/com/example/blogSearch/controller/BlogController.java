package com.example.blogSearch.controller;

import com.example.blogSearch.caller.RestTemplateApiCaller;
import com.example.blogSearch.caller.kakao.KakaoRestTemplateApiCaller;
import com.example.blogSearch.caller.naver.NaverRestTemplateApiCaller;
import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.model.SearchWordPopular;
import com.example.blogSearch.service.BlogApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/blog/")
@RequiredArgsConstructor
@Slf4j
public class BlogController {

    private final RestTemplateApiCaller restTemplateApiCaller;
//    private final KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller;
//    private final NaverRestTemplateApiCaller naverRestTemplateApiCaller;
    private final BlogApiService blogApiService;


    /**
     * @param  = 0 이면 최대로 가져올 수 있는 블로그 전체 조회
     *           아니면 Pagination 형태로 제공
     */
    @GetMapping("/search")
    public List<BlogResponse> blogSearch(
            @RequestParam @NotNull String query,
            @RequestParam(required = false, defaultValue = "accuracy") String sort,
            @RequestParam(required = false, defaultValue = "1") @Min(value = 0, message = "페이지는 0 이상이어야 합니다.") int page,
            @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = "한 페이지에 보여질 문서는 1 이상이어야 합니다.") int size) {

        return blogApiService.blogSearch(query, sort, page, size);
    }

    /**
     * 인기 블로그 조회
     */
    @GetMapping("/keyword")
    public List<SearchWord> blogKeyword() {
        return blogApiService.searchWordPopular();
    }



//    @GetMapping("/keyword2")
//    public List<SearchWordPopular> blogKeyword2() {
//        return blogApiService.searchWordPopular2();
//    }

//    @GetMapping("/test")
//    public String test() {
//        return "test";
//    }


//    @GetMapping("/c-search")
//    public BlogResponse KakaoBlogSearch(@RequestParam @NotNull String query,
//                                        @RequestParam(required = false, defaultValue = "accuracy") String sort,
//                                        @RequestParam(required = false, defaultValue = "1")
//                                        @Min(value = 1, message = "sd")    int page,
//                                        @RequestParam(required = false, defaultValue = "10") int size) {
//
//        blogApiService.searchWordSave(query);
//        BlogResponse str = kakaoRestTemplateApiCaller.findBlogByKeyword(query, sort, page, size);
//        return str;
//    }
//
//    @GetMapping("/n-search")
//    public BlogResponse NaverBlogSearch(@RequestParam String query,
//                                        @RequestParam(required = false, defaultValue = "10") int display,
//                                        @RequestParam(required = false, defaultValue = "1") int start,
//                                        @RequestParam(required = false, defaultValue = "sim") String sort) {
//
////        kakaoApiService.searchWordSave(query);
//        BlogResponse str = naverRestTemplateApiCaller.findBlogByKeyword(query, sort, display, start);
//        return str;
//    }







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
