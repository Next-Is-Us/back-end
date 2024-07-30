package com.nextisus.project.util.constant;

public class WhitelistPaths {
    public static final String[] WHITELIST = {
            "/api/test", // 통신 테스트
            "/api/link", // 온보딩
            "/api/user/signUp", // 온보딩
            "/api/admin/accessToken", // 관리자 accessToken 발급
            "/api/doctor/accessToken", // 의사 accessToken 발급

            // Swagger
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/v3/api-docs/swagger-config"
    };
}
