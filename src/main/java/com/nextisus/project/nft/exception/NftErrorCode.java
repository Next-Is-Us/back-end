package com.nextisus.project.nft.exception;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum NftErrorCode implements BaseErrorCode {

    NFT_NOT_FOUND(NOT_FOUND,"NFT_404_1","존재하지 않는 nftId입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
