package com.nextisus.project.client.user.exception;

import com.nextisus.project.util.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
