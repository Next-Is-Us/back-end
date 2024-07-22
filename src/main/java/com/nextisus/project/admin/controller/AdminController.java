package com.nextisus.project.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminController {

    /**
     * 관리자만 사용할 수 있는 API 작성
     * ex) 게시글 등록 API
     */

    @GetMapping
    public void hello() {
        log.info("ADMIN");
    }
}
