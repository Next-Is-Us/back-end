package com.nextisus.project.condition.controller;

import com.nextisus.project.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.condition.dto.request.DateRequestDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.condition.dto.response.ConditionListResponseDtoByDate;
import com.nextisus.project.condition.service.ConditionService;
import com.nextisus.project.util.response.SuccessResponse;
import com.nextisus.project.util.user.AuthUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/condition")
@PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_SON', 'ROLE_DAUGHTER')")
public class ConditionController {

    private final ConditionService conditionService;
    private final AuthUtil authUtil;

    // 오늘의 상태 기록
    @PostMapping
    public SuccessResponse<?> createCondition(@Valid @RequestBody CreateConditionRequestDto request) {
        log.info(authUtil.getCurrentUserId()); // 사용자 PK 출력
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        conditionService.createCondition(request,userId);
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
