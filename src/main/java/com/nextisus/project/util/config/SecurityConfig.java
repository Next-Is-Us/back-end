package com.nextisus.project.util.config;

import com.nextisus.project.util.jwt.CustomAccessDeniedHandler;
import com.nextisus.project.util.jwt.JwtTokenFilter;
import com.nextisus.project.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // Swagger 관련된 경로
        private static final String[] SWAGGER_WHITELIST = {
            "swagger-ui.html/**",
            "swagger-ui/**",
            "api-docs",
            "api-docs/**",
            "api-docs/**",
            "v3/api-docs/**",
            "v3/api-docs/swagger-config"
    };

    // 테스트와 관련된 경로
    private static final String[] TEST_WHITELIST = {
        "api/test/**",
    };

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .requestMatchers(TEST_WHITELIST).permitAll()
                        .requestMatchers("/api/link", "/api/user/signUp").permitAll()
                        .requestMatchers("/api/child/**").hasAnyAuthority("ROLE_SON", "ROLE_DAUGHTER")
                        .requestMatchers("/api/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/condition/**").hasAnyAuthority("ROLE_MOM", "ROLE_SON", "ROLE_DAUGHTER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(customAccessDeniedHandler))
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
