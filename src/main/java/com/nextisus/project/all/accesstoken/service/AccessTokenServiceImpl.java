package com.nextisus.project.all.accesstoken.service;

import com.nextisus.project.all.accesstoken.dto.CreateAccessTokenRequestDto;
import com.nextisus.project.all.accesstoken.dto.CreateAccessTokenResponseDto;
import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.User;
import com.nextisus.project.repository.RoleRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.exception.EnumUtils;
import com.nextisus.project.util.jwt.JwtTokenProvider;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public CreateAccessTokenResponseDto createAccessToken(CreateAccessTokenRequestDto dto) {

        // 계정 찾기
        User user = userRepository.getByNickname(dto.getNickname());

        // Role
        List<Role> roles = user.getUserRoles().stream()
                .map(EnumUtils::fromUserRole)
                .map(roleRepository::getByRoleName)
                .collect(Collectors.toList());

        // accessToken 발급
        String accessToken = jwtTokenProvider.createToken(user.getId().toString(), roles);

        // 응답
        return CreateAccessTokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
