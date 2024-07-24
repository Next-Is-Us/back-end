package com.nextisus.project.client.healthrecord.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordResponseDto {
    Long healthRecordId;
    String recordPeriod;
    Long nftCount;
}
