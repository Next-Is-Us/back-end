package com.nextisus.project.mom.room.service;

import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.User;
import com.nextisus.project.exception.room.RoomNftNotEnough;
import com.nextisus.project.mom.room.dto.EnterRoomRequestDto;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MomRoomServiceImpl implements MomRoomService {

    private final RoomRepository roomRepository;
    private final NftRepository nftRepository;
    private final UserRepository userRepository;


    @Override
    public void enterRoom(Long userId, EnterRoomRequestDto dto) {

        // [1] 꽃피 개수 부족한지 확인

        // [1-1] 유저가 가진 꽃피 개수
        Long numOfNftUserHas = nftRepository.countByUser(userRepository.getByUser(userId));
        log.info("[유저가 가진 꽃피 개수] " + numOfNftUserHas);

        // [1-2] 방에 입장하기 위한 꽃피 개수
        Long numOfNftToEnterRoom = roomRepository.getById(dto.getRoomId()).getNecessaryNftCount();
        log.info("[방에 입장하기 위한 꽃피 개수] " + numOfNftToEnterRoom);

        Optional.ofNullable(numOfNftToEnterRoom)
                .filter(nftToEnter -> nftToEnter <= numOfNftUserHas)
                .orElseThrow(RoomNftNotEnough::new);

        // 성공
        // 이미 입장해 있는지 확인 - 연관관계 맺을 필요 X
        // 이미 입장하지 않았다면 - 연관관계 맺어야 함
    }
}
