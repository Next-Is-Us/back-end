package com.nextisus.project.all.accesstoken.controller;

import com.nextisus.project.all.accesstoken.dto.CreateAccessTokenRequestDto;
import com.nextisus.project.all.accesstoken.dto.CreateAccessTokenResponseDto;
import com.nextisus.project.all.accesstoken.service.AccessTokenService;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccessTokenController {

    private final AccessTokenService accessTokenService;

    @PostMapping("/accessToken")
    public SuccessResponse<CreateAccessTokenResponseDto> createAccessToken(@RequestBody CreateAccessTokenRequestDto dto) {
        CreateAccessTokenResponseDto res = accessTokenService.createAccessToken(dto);
        return SuccessResponse.of(res);
    }
}
