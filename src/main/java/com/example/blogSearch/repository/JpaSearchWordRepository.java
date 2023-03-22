package com.example.blogSearch.repository;

import com.example.blogSearch.common.config.PopularProperties;
import com.example.blogSearch.model.PopularWord;
import com.example.blogSearch.model.SearchWord;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

import static com.example.blogSearch.model.QSearchWord.searchWord;
import static com.example.blogSearch.model.QPopularWord.popularWord;


@Primary
@Repository
public class JpaSearchWordRepository implements SearchWordRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final PopularProperties popularProperties;

    public JpaSearchWordRepository(EntityManager em, PopularProperties popularProperties) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
        this.popularProperties = popularProperties;
    }


    @Override
    public SearchWord findByKeyword(String keyword) {
        return queryFactory
                .selectFrom(searchWord)
                .where(searchWord.keyword.eq(keyword))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();
    }

    @Override
    public List<SearchWord> findAll() {
        return queryFactory
                .selectFrom(searchWord)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetch();
    }

    @Override
    public List<PopularWord> findAllPopular() {
        return queryFactory
                .selectFrom(popularWord)
                .orderBy(popularWord.searchCount.asc())
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetch();
    }

    @Override
    public List<SearchWord> findPopular() {
        int popularCount = Integer.parseInt(popularProperties.getPopularCount());
        return queryFactory
                .selectFrom(searchWord)
                .orderBy(searchWord.searchCount.desc())
                .offset(0)
                .limit(popularCount)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetch();
    }

    @Override
    public SearchWord save(String keyword) {
        SearchWord findSearchWord = queryFactory
                .selectFrom(searchWord)
                .where(searchWord.keyword.eq(keyword))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();

        if (findSearchWord == null) {
            SearchWord searchWord = SearchWord.builder()
                    .keyword(keyword)
                    .searchCount(1)
                    .build();

            em.persist(searchWord);
            return searchWord;
        } else {
            findSearchWord.changeSearchCount(findSearchWord.getSearchCount());
            return findSearchWord;
        }
    }

    @Override
    public PopularWord popularSave(SearchWord searchWord) {
        PopularWord findPopularWord = queryFactory
                .selectFrom(popularWord)
                .where(popularWord.keyword.eq(searchWord.getKeyword()))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();

        if (findPopularWord == null) {
            PopularWord popularWord = com.example.blogSearch.model.PopularWord.builder()
                    .keyword(searchWord.getKeyword())
                    .searchCount(searchWord.getSearchCount())
                    .build();

            em.persist(popularWord);
            return popularWord;
        } else {
            findPopularWord.changeSearchCount(findPopularWord.getSearchCount());
            return findPopularWord;
        }
    }

    @Override
    public void delete(PopularWord popularWord) {
        em.remove(popularWord);
    }

}
