package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.blogSearch.model.QSearchWord.searchWord;


@Primary
@Repository
public class JpaSearchWordRepository implements SearchWordRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public JpaSearchWordRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<SearchWord> findTop10() {
        return queryFactory
                .selectFrom(searchWord)
                .orderBy(searchWord.searchCount.desc())
                .offset(0)
                .limit(10)
                .fetch();
    }

    @Override
    public void save(String keyword) {
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
        } else {
            findSearchWord.changeSearchCount(findSearchWord.getSearchCount() + 1);
        }
    }

}
