package com.nextisus.project.client.mypage.service;

import com.nextisus.project.client.mypage.dto.GetLinkResponseDto;
import com.nextisus.project.client.mypage.dto.GetMyFamilyInformationResponseDto;
import com.nextisus.project.client.mypage.dto.GetMyNicknameResponseDto;

public interface ClientMyPageService {
    GetMyNicknameResponseDto getMyNickname(Long userId);
    GetMyFamilyInformationResponseDto getMyFamilyInformation(Long userId);
    GetLinkResponseDto getLink(Long userId);
}
