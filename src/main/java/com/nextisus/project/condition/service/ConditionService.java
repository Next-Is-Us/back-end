package com.nextisus.project.condition.service;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;

public interface ConditionService {
    Long createCondition(CreateConditionRequestDto requset);
}
