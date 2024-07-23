package com.nextisus.project.healthrecord.service;

import com.nextisus.project.healthrecord.dto.response.HealthRecordResponseDto;

public interface HealthRecordService {
    HealthRecordResponseDto getHealthRecord(Long userId);
}
