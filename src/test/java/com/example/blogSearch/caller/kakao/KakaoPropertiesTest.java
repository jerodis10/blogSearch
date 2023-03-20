package com.example.blogSearch.caller.kakao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

//@EnableConfigurationProperties(KakaoProperties.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@ExtendWith(SpringExtension.class)
class KakaoPropertiesTest {

    @Autowired
    private KakaoProperties kakaoProperties;

    @Configuration
    @EnableConfigurationProperties(KakaoProperties.class)
    public static class kakaoConfigTest {
        @Value("${kakao.key}")
        private String key;

        @Test
        @DisplayName("프로퍼티 값 설정 test")
        void test() {
            assertThat(key).isEqualTo("af2408226e91805021d1adc7a9d31b36");
        }
    }

//    @Value("${kakao.key}")
//    private String key;
//
//    @DisplayName("kakoProperties value binding test")
//    @Test
//    void KakaoPropertiesTest() {
//        // given
//        System.out.println(key);
//
//        // when
//
//
//        // then
//
//
//    }

}