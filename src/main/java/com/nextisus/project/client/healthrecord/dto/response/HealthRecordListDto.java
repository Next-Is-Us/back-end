package com.nextisus.project.client.healthrecord.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordListDto {
    Long healthRecordId;
    String recordPeriod;
}
