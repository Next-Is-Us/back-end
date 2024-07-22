package com.nextisus.project.condition.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodaysConditionResponseDto {
    String date;
    String sleepTime;
    Boolean isBlushing;
    Boolean isHeadache;
    Boolean isStomachache;
    Boolean isConstipated;
    Boolean isMusclePainful;
    Boolean isSkinTroubled;
    Boolean isNumbness;
    Boolean isChilled;
    Boolean isDepressed;
    String record;
}
