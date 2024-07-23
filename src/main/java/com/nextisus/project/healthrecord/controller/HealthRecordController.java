package com.nextisus.project.healthrecord.controller;

import com.nextisus.project.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.healthrecord.service.HealthRecordService;
import com.nextisus.project.util.response.SuccessResponse;
import com.nextisus.project.util.user.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/healthInfo")
@RequiredArgsConstructor
@Slf4j
public class HealthRecordController {

    private final HealthRecordService healthRecordService;
    private final AuthUtil authUtils;

    //건강 기록 조회
    @GetMapping
    public SuccessResponse<HealthRecordResponseDto> getHealthRecord() {
        Long userId = Long.parseLong(authUtils.getCurrentUserId());
        HealthRecordResponseDto response = healthRecordService.getHealthRecord(userId);
        return SuccessResponse.of(response);
    }
}
