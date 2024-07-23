package com.nextisus.project.nft.controller;

import com.nextisus.project.domain.Nft;
import com.nextisus.project.nft.dto.response.NftResponseDto;
import com.nextisus.project.nft.service.NftService;
import com.nextisus.project.util.response.SuccessResponse;
import com.nextisus.project.util.user.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nft")
@PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_SON', 'ROLE_DAUGHTER')")
public class NftController {

    private final AuthUtil authUtil;
    private final NftService nftService;

    //nft 조회
    @GetMapping
    public SuccessResponse<Long> getNfts() {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
//        log.info("get user: {}", userId);
        Long response = nftService.getNfts(userId);
        return SuccessResponse.of(response);
    }

    //nft 상세 조회
    @GetMapping("/detail/{nftId}")
    public SuccessResponse<NftResponseDto> getDetailNft(@PathVariable Long nftId) {
        NftResponseDto response = nftService.getDetailNft(nftId);
        return SuccessResponse.of(response);
    }

}
