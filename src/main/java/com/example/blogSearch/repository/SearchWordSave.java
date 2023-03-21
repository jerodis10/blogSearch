package com.example.blogSearch.repository;

import com.example.blogSearch.common.config.PopularProperties;
import com.example.blogSearch.model.PopularWord;
import com.example.blogSearch.model.SearchWord;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
@RequiredArgsConstructor
public class SearchWordSave implements SearchWordSaveStrategy {

    private final SearchWordRepository searchWordRepository;
    private final PopularProperties popularProperties;

    @Override
    public SearchWord save(String keyword) {
        SearchWord savedSearWord = searchWordRepository.save(keyword);
        int popularCount = Integer.parseInt(popularProperties.getPopularCount());

        int popularSize = 0;
        List<PopularWord> popularWordList = searchWordRepository.findAllPopular();
        popularSize = popularWordList.size();

        if (popularSize < popularCount) {
            return searchWordBinding(searchWordRepository.popularSave(savedSearWord));
        } else if (popularSize == popularCount) {
            for (PopularWord popularWord : popularWordList) {
                if (popularWord.getSearchCount() < savedSearWord.getSearchCount()) {
                    searchWordRepository.delete(popularWord);
                    return searchWordBinding(searchWordRepository.popularSave(savedSearWord));
                }
            }
        }

        return savedSearWord;
    }

    public SearchWord searchWordBinding(PopularWord popularWord) {
        return SearchWord.builder()
                .keyword(popularWord.getKeyword())
                .searchCount(popularWord.getSearchCount())
                .build();
}
}
