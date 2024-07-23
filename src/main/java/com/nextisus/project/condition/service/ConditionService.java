package com.nextisus.project.condition.service;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.dto.request.DateRequestDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDtoByDate;
import com.nextisus.project.util.response.SuccessResponse;

public interface ConditionService {

    SuccessResponse<?> createCondition(CreateConditionRequestDto request, Long userId);

    ConditionListResponseDtoByDate getConditionByDate(Long year, Long month, Long day);

    ConditionListResponseDto getDetailConditionByDate(Long year, Long month, Long day);
}
