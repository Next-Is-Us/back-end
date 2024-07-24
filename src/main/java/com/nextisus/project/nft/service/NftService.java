package com.nextisus.project.nft.service;

import com.nextisus.project.domain.Condition;
import com.nextisus.project.nft.dto.response.NftResponseDto;


import java.util.List;

public interface NftService {
    Long getNfts(Long userId);
    void createNft(Long userId);

    NftResponseDto getDetailNft(Long nftId);
}
