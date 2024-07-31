package com.nextisus.project.exception.healthrecord;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.nextisus.project.util.constant.StaticValue.INTERNAL_SERVER_ERROR;
import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum HealthRecordErrorCode implements BaseErrorCode {
    HEALTH_RECORD_NOT_FOUND(NOT_FOUND,"HEALTH_RECORD_404_1","해당 건강기록id를 가진 건강기록이 존재하지 않습니다"),
    PDF_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR,"PDF_500","pdf파일을 넣어주세요.");
    private final int httpStatus;
    private final String code;
    private final String message;
}
