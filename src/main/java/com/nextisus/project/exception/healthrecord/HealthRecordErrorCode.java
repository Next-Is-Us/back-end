package com.nextisus.project.exception.healthrecord;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.nextisus.project.util.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum HealthRecordErrorCode implements BaseErrorCode {
    HEALTH_RECORD_NOT_FOUND(NOT_FOUND,"HEALTH_RECORD_404_1","해당 건강기록id를 가진 건강기록이 존재하지 않습니다"),
    PDF_BAD_REQUEST(BAD_REQUEST,"PDF_400","pdf파일을 넣어주세요.");
    private final int httpStatus;
    private final String code;
    private final String message;
}
