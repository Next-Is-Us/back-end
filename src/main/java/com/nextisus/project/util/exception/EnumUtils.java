package com.nextisus.project.util.exception;

import com.nextisus.project.domain.RoleName;
import com.nextisus.project.role.exception.RoleNotFoundException;

public class EnumUtils {
    public static RoleName fromString(String roleName) {
        try {
            return RoleName.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            throw new RoleNotFoundException();
        }
    }
}