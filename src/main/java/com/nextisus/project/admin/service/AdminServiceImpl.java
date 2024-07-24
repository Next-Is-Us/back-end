package com.nextisus.project.admin.service;

import com.nextisus.project.admin.dto.CreateAccessTokenRequestDto;
import com.nextisus.project.admin.dto.CreateAccessTokenResponseDto;
import com.nextisus.project.admin.dto.CreateInfoPostRequestDto;
import com.nextisus.project.admin.dto.CreateInfoPostResponseDto;
import com.nextisus.project.domain.InfoPost;
import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.User;
import com.nextisus.project.infopost.repository.InfoPostRepository;
import com.nextisus.project.role.repository.RoleRepository;
import com.nextisus.project.user.dto.SignUpResponseDto;
import com.nextisus.project.user.repository.UserRepository;
import com.nextisus.project.util.exception.EnumUtils;
import com.nextisus.project.util.jwt.JwtTokenProvider;
import com.nextisus.project.util.response.SuccessResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final InfoPostRepository infoPostRepository;

    @Override
    public CreateAccessTokenResponseDto createAccessToken(CreateAccessTokenRequestDto dto) {

        // 관리자 계정 찾기
        User adminUser = userRepository.getByNickname(dto.getNickname());

        // Role
        List<Role> roles = adminUser.getUserRoles().stream()
                .map(EnumUtils::fromUserRole)
                .map(roleRepository::getByRoleName)
                .collect(Collectors.toList());

        // accessToken 발급
        String accessToken = jwtTokenProvider.createToken(adminUser.getId().toString(), roles);

        // 응답
        return CreateAccessTokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public CreateInfoPostResponseDto createInfoPost(CreateInfoPostRequestDto dto, Long id) {

        // 관리자 계정 찾기
        User adminUser = userRepository.getByUser(id);

        // InfoPost 생성
        InfoPost infoPost = InfoPost.toEntity(dto, adminUser);
        infoPostRepository.save(infoPost);

        // 응답
        return CreateInfoPostResponseDto.builder()
                .postId(infoPost.getId())
                .build();
    }
}
