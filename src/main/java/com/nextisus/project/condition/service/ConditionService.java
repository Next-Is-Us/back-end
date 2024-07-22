package com.nextisus.project.condition.service;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.util.response.SuccessResponse;

public interface ConditionService {

    SuccessResponse<?> createCondition(CreateConditionRequestDto request);

    ConditionListResponseDto getConditionByToday();

    ConditionListResponseDto getConditionByDate(String date);
}
