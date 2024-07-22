package com.nextisus.project.role.exception;

import com.nextisus.project.util.exception.BaseException;

public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException() {
        super(RoleErrorCode.ROLE_NOT_FOUND);
    }
}
