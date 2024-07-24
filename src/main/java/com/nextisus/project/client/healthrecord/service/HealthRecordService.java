package com.nextisus.project.client.healthrecord.service;

import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.domain.HealthRecord;

public interface HealthRecordService {
    HealthRecordResponseDto getHealthRecord(Long userId);
    HealthRecord createHealthRecord(Long userId);
}
