package com.nextisus.project.client.mypage.controller;

import com.nextisus.project.client.mypage.service.ClientMyPageService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myPage")
@RequiredArgsConstructor
public class ClientMyPageController {

    private final AuthUtil authUtil;
    private final ClientMyPageService clientMyPageService;

    @GetMapping("/familyInformation")
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DAUGHTER', 'ROLE_SON', 'ROLE_ADMIN')")
    public SuccessResponse<GetMyFamilyInformationResponseDto> getMyFamilyInformation() {
        long userId = Long.parseLong(authUtil.getCurrentUserId());
        GetMyFamilyInformationResponseDto res = clientMyPageService.getMyFamilyInformation(userId);
        return SuccessResponse.of(res);
    }

    @GetMapping("/link")
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DAUGHTER', 'ROLE_SON', 'ROLE_ADMIN')")
    public SuccessResponse<GetLinkResponseDto> getLink() {
        long userId = Long.parseLong(authUtil.getCurrentUserId());
        GetLinkResponseDto res = clientMyPageService.getLink(userId);
        return SuccessResponse.of(res);
    }
}
