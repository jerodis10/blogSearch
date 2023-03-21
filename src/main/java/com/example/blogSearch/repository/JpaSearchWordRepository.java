package com.example.blogSearch.repository;

import com.example.blogSearch.common.config.PopularProperties;
import com.example.blogSearch.model.SearchWord;
import com.example.blogSearch.model.SearchWordPopular;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.example.blogSearch.model.QSearchWord.searchWord;
import static com.example.blogSearch.model.QSearchWordPopular.searchWordPopular;


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
                .fetchOne();
    }

    @Override
    public List<SearchWord> findAll() {
        return queryFactory
                .selectFrom(searchWord)
                .fetch();
    }

    @Override
    public List<SearchWordPopular> findAllPopular() {
        return queryFactory
                .selectFrom(searchWordPopular)
                .orderBy(searchWordPopular.searchCount.asc())
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
                .fetch();

    }

    @Override
    public SearchWord save(String keyword) {
        SearchWord findSearchWord = queryFactory
                .selectFrom(searchWord)
                .where(searchWord.keyword.eq(keyword))
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
    public SearchWordPopular popularSave(SearchWord searchWord) {
        SearchWordPopular findSearchWordPopular = queryFactory
                .selectFrom(searchWordPopular)
                .where(searchWordPopular.keyword.eq(searchWord.getKeyword()))
                .fetchOne();

        if (findSearchWordPopular == null) {
            SearchWordPopular searchWordPopular = SearchWordPopular.builder()
                    .keyword(searchWord.getKeyword())
                    .searchCount(searchWord.getSearchCount())
                    .build();

            em.persist(searchWordPopular);
            return searchWordPopular;
        } else {
            findSearchWordPopular.changeSearchCount(findSearchWordPopular.getSearchCount());
            return findSearchWordPopular;
        }
    }

    @Override
    public void delete(SearchWordPopular searchWordPopular) {
        em.remove(searchWordPopular);
    }

}
