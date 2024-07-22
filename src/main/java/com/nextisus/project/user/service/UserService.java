package com.nextisus.project.user.service;

import com.nextisus.project.user.dto.SignUpRequestDto;

public interface UserService {
    Long signUp (SignUpRequestDto dto);
}
