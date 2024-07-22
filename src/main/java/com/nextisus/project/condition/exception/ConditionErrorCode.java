package com.nextisus.project.condition.exception;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ConditionErrorCode implements BaseErrorCode {

    CONDITION_NOT_FOUND(NOT_FOUND,"CONDITION_404_1","해당 날짜에 기록한 상태가 존재하지 않습니다");

    private final int httpStatus;
    private final String code;
    private final String message;
}
