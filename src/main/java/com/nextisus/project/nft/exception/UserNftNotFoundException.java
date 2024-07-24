package com.nextisus.project.nft.exception;

import com.nextisus.project.util.exception.BaseException;

public class UserNftNotFoundException extends BaseException {
    public UserNftNotFoundException() {
        super(NftErrorCode.NFT_NOT_FOUND_2);
    }
}
