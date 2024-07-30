package com.nextisus.project.client.mypage.service;

import com.nextisus.project.repository.LinkRepository;
import com.nextisus.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMyPageServiceImpl implements ClientMyPageService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;
}
