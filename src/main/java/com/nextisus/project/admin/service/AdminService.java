package com.nextisus.project.admin.service;

import com.nextisus.project.admin.dto.CreateAccessTokenRequestDto;
import com.nextisus.project.admin.dto.CreateAccessTokenResponseDto;

public interface AdminService {
    CreateAccessTokenResponseDto createAccessToken(CreateAccessTokenRequestDto dto);
}
