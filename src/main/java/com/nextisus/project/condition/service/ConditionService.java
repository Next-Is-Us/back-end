package com.nextisus.project.condition.service;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.dto.request.DateRequestDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDtoByDate;
import com.nextisus.project.util.response.SuccessResponse;

public interface ConditionService {

    SuccessResponse<?> createCondition(CreateConditionRequestDto request);

    ConditionListResponseDtoByDate getConditionByDate(DateRequestDto date);

    ConditionListResponseDto getDetailConditionByDate(DateRequestDto date);
}
