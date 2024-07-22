package com.nextisus.project.user.exception;

import com.nextisus.project.util.exception.BaseException;

public class UserNicknameDuplicatedException extends BaseException {
    public UserNicknameDuplicatedException() {
        super(UserErrorCode.USER_DUPLICATED);
    }
}
