package com.nextisus.project.client.healthrecord.service;

import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;

public interface HealthRecordService {
    HealthRecordResponseDto getHealthRecord(Long userId);
    void createHealthRecord(Long userId);
}
