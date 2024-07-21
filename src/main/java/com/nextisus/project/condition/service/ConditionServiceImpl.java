package com.nextisus.project.condition.service;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService{

    @Override
    public Long createCondition(CreateConditionRequestDto requset) {
        return null;
    }
}
