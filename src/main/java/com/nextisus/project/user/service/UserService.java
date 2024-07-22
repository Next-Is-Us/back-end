package com.nextisus.project.user.service;

import com.nextisus.project.user.dto.SignUpRequestDto;
import com.nextisus.project.user.dto.SignUpResponseDto;

public interface UserService {
    SignUpResponseDto signUp (SignUpRequestDto dto);
}
