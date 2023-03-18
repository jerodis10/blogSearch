package com.example.blogSearch.controller;

import com.example.blogSearch.caller.KakaoProperties;
import com.example.blogSearch.caller.KakaoRestTemplateApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/")
@RequiredArgsConstructor
public class BlogController {

    private final KakaoRestTemplateApiCaller kakaoRestTemplateApiCaller;

//    private final KakaoProperties kakaoProperties;

    @GetMapping("/search")
    public String blogSearch(@RequestParam String query) {
        String str = kakaoRestTemplateApiCaller.findBlogByKeyword(query);
//        System.out.println(kakaoProperties);
        return str;
    }



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
