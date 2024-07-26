package com.nextisus.project.exception.healthrecord;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum HealthRecordErrorCode implements BaseErrorCode {
    HEALTH_RECORD_NOT_FOUND(NOT_FOUND,"CONDITION_404_1","해당 건강기록id를 가진 건강기록이 존재하지 않습니다");

    private final int httpStatus;
    private final String code;
    private final String message;
}
