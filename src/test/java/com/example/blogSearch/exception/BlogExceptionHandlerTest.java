package com.example.blogSearch.exception;

import com.example.blogSearch.common.dto.BlogResponse;
import com.example.blogSearch.service.BlogApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RestClientTest
class BlogExceptionHandlerTest {

    private static final String BASE_URL = "https://dapi.kakao.com";
    private static final String QUERY = "query";

    @Autowired
    private RestTemplateBuilder builder;

    @Mock
    private BlogApiService blogApiService;

    @Autowired
    MockMvc mockMvc;

    private MockRestServiceServer server;
    private RestTemplate restTemplate;

    private static Stream<Arguments> provideErrorCode() {
        return Stream.of(
                Arguments.of(HttpStatus.BAD_REQUEST),
                Arguments.of(HttpStatus.UNAUTHORIZED),
                Arguments.of(HttpStatus.FORBIDDEN),
                Arguments.of(HttpStatus.INTERNAL_SERVER_ERROR),
                Arguments.of(HttpStatus.BAD_GATEWAY),
                Arguments.of(HttpStatus.SERVICE_UNAVAILABLE)
        );
    }

    @BeforeEach
    private void setUp() {
        restTemplate = this.builder
                .errorHandler(new BlogExceptionHandler())
                .build();
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @DisplayName("Exception Handler test")
    @ParameterizedTest
    @MethodSource("provideErrorCode")
    public void exceptionHandlerTest(HttpStatus status) {
        this.server
                .expect(ExpectedCount.once(), requestTo(BASE_URL + QUERY))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(status));

        assertThatThrownBy(() -> restTemplate.getForObject(BASE_URL + QUERY, BlogResponse.class))
                .isInstanceOf(BlogException.class)
                .extracting("errorCode")
                .asString()
                .isEqualTo(status.name());
    }

}