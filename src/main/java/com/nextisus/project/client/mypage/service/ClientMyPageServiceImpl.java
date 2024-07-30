package com.nextisus.project.client.mypage.service;

import com.nextisus.project.client.mypage.dto.GetLinkResponseDto;
import com.nextisus.project.client.mypage.dto.GetMyFamilyInformationResponseDto;
import com.nextisus.project.client.mypage.dto.GetMyNicknameResponseDto;
import com.nextisus.project.domain.User;
import com.nextisus.project.repository.LinkRepository;
import com.nextisus.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMyPageServiceImpl implements ClientMyPageService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;

    @Override
    public GetMyNicknameResponseDto getMyNickname(Long userId) {
        User user = userRepository.getByUser(userId);
        return GetMyNicknameResponseDto.of(user.getNickname());
    }

    @Override
    public GetMyFamilyInformationResponseDto getMyFamilyInformation(Long userId) {
        return null;
    }

    @Override
    public GetLinkResponseDto getLink(Long userId) {
        return null;
    }
}
