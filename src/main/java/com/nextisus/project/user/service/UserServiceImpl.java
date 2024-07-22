package com.nextisus.project.user.service;

import com.nextisus.project.link.repository.LinkRepository;
import com.nextisus.project.user.dto.SignUpRequestDto;
import com.nextisus.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;

    @Override
    public Long signUp(SignUpRequestDto dto) {
        return null;
    }


}
