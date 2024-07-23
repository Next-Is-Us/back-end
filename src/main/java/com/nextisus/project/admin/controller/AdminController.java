package com.nextisus.project.admin.controller;

import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    /**
     * 관리자만 사용할 수 있는 API 작성
     * ex) 게시글 등록 API
     */

    // 관리자 accessToken 발급
    @PostMapping("/accessToken")
    public void createAccessToken() {
        log.info("ADMIN: createAccessToken");
    }

    // 관리자가 InfoPost 생성
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public SuccessResponse<?> createPost() {
        log.info("ADMIN: createPost");
        return null;
    }
}
