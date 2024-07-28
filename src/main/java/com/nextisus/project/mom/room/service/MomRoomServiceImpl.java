package com.nextisus.project.mom.room.service;

import com.nextisus.project.mom.room.dto.EnterRoomRequestDto;
import com.nextisus.project.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomRoomServiceImpl implements MomRoomService {

    private final RoomRepository roomRepository;

    @Override
    public void enterRoom(Long userId, EnterRoomRequestDto dto) {

        // 꽃피 개수 부족한지 확인

        // 이미 입장해 있는지 확인

        // 성공
    }
}
