package com.example.blogSearch.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class LoginUserAuditorAware implements AuditorAware<String> {
    private final HttpSession httpSession;

    /**
     * 유저 정보는 세션에서 가져오고, 없을 경우 null을 반환한다.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        if (httpSession.getAttribute("user") == null) {
            return Optional.ofNullable("master");
        }

        return Optional.ofNullable(httpSession.getAttribute("user").toString());
    }

}
