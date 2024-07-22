package com.nextisus.project.condition.controller;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.dto.request.DateRequestDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDtoByDate;
import com.nextisus.project.condition.service.ConditionService;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/condition")
@RequiredArgsConstructor
public class ConditionController {

    private final ConditionService conditionService;

    // 오늘의 상태 기록
    @PostMapping
    public SuccessResponse<?> createCondition(@Valid @RequestBody CreateConditionRequestDto request) {
        conditionService.createCondition(request);
        return SuccessResponse.empty();
    }

    // 날짜 별 상태 여부 조회
    @GetMapping("/by-date")
    public SuccessResponse<ConditionListResponseDtoByDate> getConditionByDate(@Valid @RequestBody DateRequestDto date) {
        ConditionListResponseDtoByDate response = conditionService.getConditionByDate(date);
        return SuccessResponse.of(response);
    }

    // 날짜 별 상태 기록 세부 조회
    @GetMapping("/detail")
    public SuccessResponse<ConditionListResponseDto> getDetailConditionByDate(@Valid @RequestBody DateRequestDto date) {
        ConditionListResponseDto response = conditionService.getDetailConditionByDate(date);
        return SuccessResponse.of(response);
    }
}
