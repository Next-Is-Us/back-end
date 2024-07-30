package com.nextisus.project.client.mypage.service;

import com.nextisus.project.client.mypage.dto.GetLinkResponseDto;
import com.nextisus.project.client.mypage.dto.GetMyFamilyInformationResponseDto;
import com.nextisus.project.client.mypage.dto.GetMyNicknameResponseDto;
import com.nextisus.project.domain.Link;
import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.RoleName;
import com.nextisus.project.domain.User;
import com.nextisus.project.domain.UserRole;
import com.nextisus.project.repository.LinkRepository;
import com.nextisus.project.repository.RoleRepository;
import com.nextisus.project.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMyPageServiceImpl implements ClientMyPageService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;
    private final RoleRepository roleRepository;

    @Override
    public GetMyNicknameResponseDto getMyNickname(Long userId) {
        User user = userRepository.getByUser(userId);
        return GetMyNicknameResponseDto.of(user.getNickname());
    }

    @Override
    public List<GetMyFamilyInformationResponseDto> getMyFamilyInformation(Long userId) {
        User user = userRepository.getByUser(userId);
        Link link = linkRepository.getByLink(user.getLink().getLink());

        List<RoleName> roleNames = Arrays.asList(RoleName.ROLE_MOM, RoleName.ROLE_SON, RoleName.ROLE_DAUGHTER);
        List<Role> roles = roleNames.stream()
                .map(roleRepository::getByRoleName)
                .toList();

        List<UserRole> userRoles = roles.stream()
                .map(role -> UserRole.builder().role(role).build())
                .collect(Collectors.toList());

        List<User> allUsers = userRepository.findAllByLinkAndUserRoles(link, userRoles);

        return allUsers.stream()
                .map(GetMyFamilyInformationResponseDto::from)
                .collect(Collectors.toList());

        // TODO: 현재 유저는 제외하기
    }

    @Override
    public GetLinkResponseDto getLink(Long userId) {
        return null;
    }
}
