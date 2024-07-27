package com.nextisus.project.doctor.room.service;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import com.nextisus.project.doctor.room.dto.CreateRoomResponseDto;
import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.User;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorRoomServiceImpl implements DoctorRoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public CreateRoomResponseDto createRoom(CreateRoomRequestDto dto, Long userId) {

        // 사용자 존재하는지 확인
        User user = userRepository.getByUser(userId);

        // 룸 생성
        Long roomId = roomRepository.save(Room.toEntity(dto)).getId();
        return CreateRoomResponseDto.builder().roomId(roomId).build();

    }
}
