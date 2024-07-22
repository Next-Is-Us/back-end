package com.nextisus.project.condition.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateConditionRequestDto {

    @NotNull(message = "작성자의 기본키를 입력해주세요.")
    Long userId;

    @NotBlank(message = "수면 시간을 입력해주세요")
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

    @NotBlank
    @Size(max = 300, message = "추가 기록은 최대 300자 입니다.")
    String record;
}
