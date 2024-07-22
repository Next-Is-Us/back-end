package com.nextisus.project.condition.service;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.dto.response.TodaysConditionResponseDto;
import com.nextisus.project.util.response.SuccessResponse;
import org.springframework.http.ResponseEntity;

public interface ConditionService {

    SuccessResponse<?> createCondition(CreateConditionRequestDto request);

    TodaysConditionResponseDto getTodaysConditions();
}
