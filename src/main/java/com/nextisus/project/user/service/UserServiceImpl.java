package com.nextisus.project.user.service;

import com.nextisus.project.domain.Link;
import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.RoleName;
import com.nextisus.project.domain.User;
import com.nextisus.project.link.repository.LinkRepository;
import com.nextisus.project.role.RoleRepository;
import com.nextisus.project.user.dto.SignUpRequestDto;
import com.nextisus.project.user.repository.UserRepository;
import com.nextisus.project.util.jwt.JwtTokenProvider;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Long signUp(SignUpRequestDto dto) {


        Optional<Link> byLink = linkRepository.findByLink(dto.getLink());

        // 링크가 존재하는지 확인 - 존재하지 않으면 회원가입 불가 -> LinkNotFoundException
        if (byLink.isEmpty()) {
            log.info("링크가 존재하지 않음");
            throw new RuntimeException("링크가 존재하지 않음");
        }

        // 해당 링크에서 닉네임이 중복인지 확인 - 중복이라면 회원가입 불가 -> NicknameDuplicatedException
        if (userRepository.findByLinkAndNickname(byLink.get(), dto.getNickname()).isPresent()) {
            log.info("같은 링크 내 닉네임이 중복임");
            throw new RuntimeException("같은 링크 내 닉네임이 중복임");
        }

        // 회원가입 가능
        List<Role> roles = dto.getUserRoles().stream()
                .map(roleName -> roleRepository.findByRoleName(RoleName.valueOf(roleName))
                        .orElseThrow(() -> new IllegalArgumentException(roleName + "은 존재하지 않는 역할입니다.")))
                .collect(Collectors.toList());

        User user = User.toEntity(dto, roles, byLink.get());
        userRepository.save(user);

        return user.getId();
    }
}
