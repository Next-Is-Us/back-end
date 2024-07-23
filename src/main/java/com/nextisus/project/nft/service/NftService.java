package com.nextisus.project.nft.service;

import com.nextisus.project.domain.Condition;

import java.util.List;

public interface NftService {
    Long getNfts(Long userId);
    void createNft(int conditionSize, List<Condition> conditions, Long userId);
}
