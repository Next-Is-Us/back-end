package com.nextisus.project.client.healthrecord.controller;

import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.client.healthrecord.service.HealthRecordService;
import com.nextisus.project.domain.HealthRecord;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/healthRecord")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_SON', 'ROLE_DAUGHTER')")
public class HealthRecordController {

    private final HealthRecordService healthRecordService;
    private final AuthUtil authUtils;

    // 건강 기록 조회
    @GetMapping
    public SuccessResponse<HealthRecordResponseDto> getHealthRecord() {
        Long userId = Long.parseLong(authUtils.getCurrentUserId());
        HealthRecordResponseDto response = healthRecordService.getHealthRecord(userId);
        return SuccessResponse.of(response);
    }

    //건강 기록 세부 조회 (카드 컴포넌트 클릭 시)
    @GetMapping("/detail/{healthRecordId}")
    public SuccessResponse<HealthRecordResponseDto> getHealthRecordDetail(@PathVariable Long healthRecordId) {
        Long userId = Long.parseLong(authUtils.getCurrentUserId());
        HealthRecordResponseDto response = healthRecordService.getHealthRecordDetail(userId,healthRecordId);
        return SuccessResponse.of(response);
    }
}
