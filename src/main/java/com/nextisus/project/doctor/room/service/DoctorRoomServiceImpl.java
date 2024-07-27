package com.nextisus.project.doctor.room.service;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import com.nextisus.project.doctor.room.dto.CreateRoomResponseDto;
import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.User;
import com.nextisus.project.domain.UserRoom;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorRoomServiceImpl implements DoctorRoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public CreateRoomResponseDto createRoom(CreateRoomRequestDto dto, Long userId) {

        // 사용자 존재하는지 확인
        User user = userRepository.getByUser(userId);

        System.out.println(user.getNickname());

        // 룸 생성 및 저장
        Room room = roomRepository.save(Room.toEntity(dto));

        // 사용자와 룸 간의 연관관계 설정
        UserRoom userRoom = new UserRoom(user, room);

        // 변경된 엔티티를 저장
        userRepository.save(user);
        roomRepository.save(room);

        return CreateRoomResponseDto.builder().roomId(room.getId()).build();
    }
}
