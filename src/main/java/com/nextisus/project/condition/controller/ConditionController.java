package com.nextisus.project.condition.controller;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.dto.response.TodaysConditionResponseDto;
import com.nextisus.project.condition.service.ConditionService;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.Getter;
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
    public SuccessResponse<?> createCondition(@Valid @RequestBody CreateConditionRequestDto request) {
        conditionService.createCondition(request);
        return SuccessResponse.empty();
    }

    // 오늘 날짜로 기록한 상태 조회
    @GetMapping
    public SuccessResponse<TodaysConditionResponseDto> getConditionByToday() {
        TodaysConditionResponseDto response = conditionService.getConditionByToday();
        return SuccessResponse.of(response);
    }

    // 날짜별 상태 조회
    public SuccessResponse<TodaysConditionResponseDto> getConditionByDate() {
        return null;
    }
}
