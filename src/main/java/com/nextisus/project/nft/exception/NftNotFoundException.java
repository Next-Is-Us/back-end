package com.nextisus.project.nft.exception;

import com.nextisus.project.util.exception.BaseException;

public class NftNotFoundException extends BaseException {
    public NftNotFoundException() {
        super(NftErrorCode.NFT_NOT_FOUND);
    }
}
