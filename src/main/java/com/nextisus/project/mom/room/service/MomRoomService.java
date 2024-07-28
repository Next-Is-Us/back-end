package com.nextisus.project.mom.room.service;

import com.nextisus.project.mom.room.dto.EnterRoomRequestDto;

public interface MomRoomService {
    void enterRoom(Long userId, EnterRoomRequestDto dto);
}
