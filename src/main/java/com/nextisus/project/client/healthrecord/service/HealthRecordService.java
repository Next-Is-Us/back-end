package com.nextisus.project.client.healthrecord.service;

import com.nextisus.project.client.healthrecord.dto.response.HealthRecordListDto;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.domain.HealthRecord;

import java.util.List;

public interface HealthRecordService {
    List<HealthRecordListDto> getHealthRecord(Long userId);
    HealthRecord createHealthRecord(Long userId,Long countNft);
    HealthRecordResponseDto getHealthRecordDetail(Long healthRecordId);
}
