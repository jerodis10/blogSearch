package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JpaSearchWordRepositoryTest {

    private final int searchCount = 0;
    private final int threadCount = 300;
    private ExecutorService executorService;
    private CountDownLatch countDownLatch;

    @Autowired
    private SearchWordRepository searchWordRepository;

    @BeforeEach
    void setUp() {
        searchWordRepository.save("word");
        executorService = Executors.newFixedThreadPool(threadCount);
        countDownLatch = new CountDownLatch(threadCount);
    }

    @DisplayName("[Repository] 검색어 조회 test")
    @Test
    void searchWordSortingTest() {
        // given
        String keyword = "word";
        SearchWord searchWord = searchWordRepository.findByKeyword(keyword);

        // when then
        assertThat(searchWord.getKeyword()).isEqualTo(keyword);
    }

    @DisplayName("[Repository] 인기 검색어 save test")
    @Test
    void saveTest() {
        // given
        String keyword = "word";

        // when
        SearchWord searchWord = searchWordRepository.save(keyword);

        // then
        assertThat(searchWord).isNotNull();
    }

    @Test
    @DisplayName("[Repository] 단일 쓰레드일 경우 searchCount update test")
    void updateTest() {
        // given
        String keyword = "word";

        // when
        SearchWord searchWord = searchWordRepository.findByKeyword(keyword);

        //then
        assertThat(searchWord.getSearchCount()).isEqualTo(searchCount + 1);
    }

    @Test
    @DisplayName("[Repository] 멀티 쓰레드일 경우 searchCount update test")
    void updateMultiTest() throws InterruptedException {
        // given
        String keyword = "word";

        // when
        IntStream.range(0, threadCount).forEach(e ->
            executorService.submit(() -> {
                    try {
                        searchWordRepository.save(keyword);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            ));
            countDownLatch.await();

        //then
        SearchWord searchWord = searchWordRepository.findByKeyword(keyword);
        assertThat(searchWord.getSearchCount()).isEqualTo(searchCount + 1);
    }


}