package com.nextisus.project.condition.controller;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.service.ConditionService;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/condition")
@RequiredArgsConstructor
public class ConditionController {
    private final ConditionService conditionService;

    //오늘의 상태 기록
    @PostMapping
    public SuccessResponse<Long> createCondition(
            @Valid @RequestBody CreateConditionRequestDto requset
    ){
        Long conditionId = conditionService.createCondition(requset);
        return SuccessResponse.of(conditionId);
    }
}
