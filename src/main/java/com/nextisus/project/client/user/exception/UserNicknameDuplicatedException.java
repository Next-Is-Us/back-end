package com.nextisus.project.client.user.exception;

import com.nextisus.project.util.exception.BaseException;

public class UserNicknameDuplicatedException extends BaseException {
    public UserNicknameDuplicatedException() {
        super(UserErrorCode.USER_DUPLICATED);
    }
}
