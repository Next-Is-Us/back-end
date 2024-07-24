package com.nextisus.project.util.constant;

public class WhitelistPaths {
    public static final String[] WHITELIST = {
            "/api/test", // 통신 테스트

            "/api/link", // 온보딩
            "/api/user/signUp", // 온보딩

            "/api/admin/accessToken", // 관리자 건강 정보 게시글 작성

            // Swagger
            "/swagger-ui.html/**",
            "/swagger-ui/**",
            "/api-docs",
            "/api-docs/**",
            "/v3/api-docs/**",
            "/v3/api-docs/swagger-config",


    };
}
