package com.nextisus.project.doctor.room.service;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import com.nextisus.project.doctor.room.dto.CreateRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorRoomServiceImpl implements DoctorRoomService {

    @Override
    public CreateRoomResponseDto createRoom(CreateRoomRequestDto dto, Long userId) {
        return null;
    }
}
