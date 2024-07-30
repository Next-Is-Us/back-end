package com.nextisus.project.doctor.accesstoken.controller;

import com.nextisus.project.doctor.accesstoken.dto.CreateAccessTokenRequestDto;
import com.nextisus.project.doctor.accesstoken.dto.CreateAccessTokenResponseDto;
import com.nextisus.project.doctor.accesstoken.service.DoctorService;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/accessToken")
    public SuccessResponse<CreateAccessTokenResponseDto> createAccessToken(@RequestBody CreateAccessTokenRequestDto dto) {
        CreateAccessTokenResponseDto res = doctorService.createAccessToken(dto);
        return SuccessResponse.of(res);
    }
}
