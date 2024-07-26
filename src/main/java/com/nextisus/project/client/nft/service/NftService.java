package com.nextisus.project.client.nft.service;

import com.nextisus.project.client.nft.dto.response.NftResponseDto;
import com.nextisus.project.domain.Nft;

public interface NftService {
    Long getNfts(Long userId);
    Nft createNft(Long userId);

    NftResponseDto getDetailNft(Long nftId);
}
