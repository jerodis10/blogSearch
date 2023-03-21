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
     * 만약 SpringSecurity 를 쓴다면 SecurityContext 에서 인증정보를 가져와 주입시킨다.
     * 현재 코드는 현재 Context 유저가 user 인가 권한이 있으면, 해당 Principal name 을 대입하고, 아니면 Null 을 set 한다.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        if (httpSession.getAttribute("user") == null) {
            return Optional.ofNullable("master");
        }

        return Optional.ofNullable(httpSession.getAttribute("user").toString());
    }

}
