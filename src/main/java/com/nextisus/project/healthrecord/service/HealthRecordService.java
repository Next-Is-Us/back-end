package com.nextisus.project.healthrecord.service;

import com.nextisus.project.domain.Nft;
import com.nextisus.project.healthrecord.dto.response.HealthRecordResponseDto;

import java.util.List;

public interface HealthRecordService {
    HealthRecordResponseDto getHealthRecord(Long userId);
    void createHealthRecord(Long userId);
}
