package com.nextisus.project.mom.room.service;

import com.nextisus.project.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomRoomServiceImpl implements MomRoomService {

    private final RoomRepository roomRepository;

}
