package com.nextisus.project.client.link.exception;

import com.nextisus.project.util.exception.BaseException;

public class LinkNotFoundException extends BaseException {
    public LinkNotFoundException() {
        super(LinkErrorCode.LINK_NOT_FOUND);
    }
}
