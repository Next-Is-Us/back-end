package com.nextisus.project.client.nft.service;

import com.nextisus.project.client.nft.dto.response.NftResponseDto;

public interface NftService {
    Long getNfts(Long userId);
    void createNft(Long userId);

    NftResponseDto getDetailNft(Long nftId);
}
