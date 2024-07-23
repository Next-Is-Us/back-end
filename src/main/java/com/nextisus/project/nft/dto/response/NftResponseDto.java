package com.nextisus.project.nft.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NftResponseDto {
    private Long nftId;
    private String recordPeriod;
    private String week;
}
