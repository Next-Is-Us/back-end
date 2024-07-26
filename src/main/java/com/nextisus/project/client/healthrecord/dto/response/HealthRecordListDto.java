package com.nextisus.project.client.healthrecord.dto.response;

import com.nextisus.project.domain.HealthRecord;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordListDto {
    Long healthRecordId;
    String recordPeriod;
    public static HealthRecordListDto from(HealthRecord healthRecord) {
        return new HealthRecordListDto(
                healthRecord.getHealthRecordId(),
                healthRecord.getRecordPeriod()
        );
    }
}
