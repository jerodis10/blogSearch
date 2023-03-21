package com.example.blogSearch.repository;

import com.example.blogSearch.model.SearchWord;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@DataJpaTest
@ExtendWith(MockitoExtension.class)
//@Import(TestConfig.class)
//@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Import(SearchWordRepository.class)
//@Import(JpaSearchWordRepository.class)
class JpaSearchWordRepositoryTest2 {

//    @Mock
//    private EntityManager em;
//
    @Autowired
    JPAQueryFactory queryFactory;

//    @Autowired
//    private JpaSearchWordRepository jpaSearchWordRepository;
//
//    @Autowired
//    private SearchWordRepository searchWordRepository;

    @InjectMocks
    private JpaSearchWordRepository jpaSearchWordRepository;

//    @Mock
//    JPAQueryFactory queryFactory;

//    @BeforeEach
//    public void init() {
//        queryFactory = new JPAQueryFactory(em);
//    }

    @DisplayName("[Repository] 검색어 counting test")
    @Test
    void test() {
        System.out.println(jpaSearchWordRepository.findTop10());
    }


}