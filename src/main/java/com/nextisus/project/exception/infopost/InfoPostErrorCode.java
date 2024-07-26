package com.nextisus.project.exception.infopost;

import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfoPostErrorCode implements BaseErrorCode {

    INFO_POST_NOT_FOUND(NOT_FOUND,"INFO_POST_404","게시글이 존재하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
