package com.nextisus.project.client.condition.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionListResponseDtoByDate {
    Boolean isRecoding;
    String date;
}
