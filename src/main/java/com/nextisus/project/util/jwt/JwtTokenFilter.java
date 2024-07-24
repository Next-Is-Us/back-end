package com.nextisus.project.util.jwt;

import com.nextisus.project.util.auth.AuthErrorCode;
import com.nextisus.project.util.exception.BaseException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = jwtTokenProvider.resolveToken(request);

        try {
            if (StringUtils.hasText(token)) {
                if (!jwtTokenProvider.validateToken(token)) {
                    throw new BaseException(AuthErrorCode.INVALID_AUTH_TOKEN);
                }
                jwtTokenProvider.setSecurityContext(token);
            } else {
                throw new BaseException(AuthErrorCode.MISSING_AUTH_TOKEN);
            }

            filterChain.doFilter(servletRequest, servletResponse);

        } catch (BaseException e) {
            response.setStatus(e.getErrorCode().getHttpStatus());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.format(
                    "{\"timestamp\":\"%s\",\"isSuccess\":false,\"code\":\"%s\",\"message\":\"%s\",\"httpStatus\":%d}",
                    LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                    e.getErrorCode().getCode(),
                    e.getErrorCode().getMessage(),
                    e.getErrorCode().getHttpStatus()
            ));
        }
    }
}
