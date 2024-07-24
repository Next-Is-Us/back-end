package com.nextisus.project.mom.condition.exception;

import com.nextisus.project.util.exception.BaseException;

public class ConditionNotFoundException extends BaseException {
    public ConditionNotFoundException() {
        super(ConditionErrorCode.CONDITION_NOT_FOUND_1);
    }
}
