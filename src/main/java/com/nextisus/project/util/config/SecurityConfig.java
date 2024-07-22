package com.nextisus.project.util.config;

import com.nextisus.project.util.jwt.JwtTokenFilter;
import com.nextisus.project.util.jwt.JwtTokenProvider;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Swagger와 관련된 URI
     * 아무나 접근 가능
     * 실 서비스 시에는 막아야 함
     */
    private static final String[] SWAGGER_WHITELIST = {
            "swagger-ui.html/**",
            "swagger-ui/**",
            "api-docs",
            "api-docs/**",
            "api-docs/**",
            "v3/api-docs/**",
            "v3/api-docs/swagger-config"
    };

    /**
     * 아무나 접근 가능
     * 실 서비스 시에는 막아야 함
     */
    private static final String[] TEST_WHITELIST = {
//            "api/test/**",
            "api/**",
    };

    /**
     * 모두가 접근 가능한 URI 정의
     */
    private static final String[] PERMIT_ALL_URI;

    static {
        PERMIT_ALL_URI = Arrays.copyOf(SWAGGER_WHITELIST, SWAGGER_WHITELIST.length + TEST_WHITELIST.length);
        System.arraycopy(TEST_WHITELIST, 0, PERMIT_ALL_URI, SWAGGER_WHITELIST.length, TEST_WHITELIST.length);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PERMIT_ALL_URI);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                /**
                 *  최종 배포 서버에서 반영하기
                 *  로컬에서 테스트 시 방해됨
                 */
//                .requiresChannel((channel) -> channel.anyRequest().requiresSecure())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(PERMIT_ALL_URI)
                        .permitAll())
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
