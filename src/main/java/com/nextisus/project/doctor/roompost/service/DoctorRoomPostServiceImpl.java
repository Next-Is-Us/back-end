package com.nextisus.project.doctor.roompost.service;

import com.nextisus.project.doctor.roompost.dto.CreateRoomPostRequestDto;
import com.nextisus.project.doctor.roompost.dto.CreateRoomPostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorRoomPostServiceImpl implements DoctorRoomPostService {

    @Override
    public CreateRoomPostResponseDto createRoomPost(Long userId, CreateRoomPostRequestDto dto) {
        return null;
    }
}
