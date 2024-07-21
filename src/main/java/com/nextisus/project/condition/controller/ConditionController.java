package com.nextisus.project.condition.controller;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.service.ConditionService;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/condition")
@RequiredArgsConstructor
public class ConditionController {

    private final ConditionService conditionService;

    // 오늘의 상태 기록
    @PostMapping
    public SuccessResponse<CreateConditionRequestDto> createCondition(@Valid @RequestBody CreateConditionRequestDto request) {
        conditionService.createCondition(request);
        return SuccessResponse.empty();
    }
}
