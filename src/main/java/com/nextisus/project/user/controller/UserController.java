package com.nextisus.project.user.controller;

import com.nextisus.project.user.dto.SignUpRequestDto;
import com.nextisus.project.user.service.UserService;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test/user")
public class UserController {

    /**
     * 엄마와 자녀가 공통으로 사용할 수 있는 API 작성
     * ex) 회원가입, 로그인, 로그아웃 등
     */

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/signUp")
    public SuccessResponse<Long> signUp(@RequestBody SignUpRequestDto dto) {
        Long userId = userService.signUp(dto);
        return SuccessResponse.of(userId);
    }

}
