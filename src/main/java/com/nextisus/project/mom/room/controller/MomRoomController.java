package com.nextisus.project.mom.room.controller;

import com.nextisus.project.mom.room.dto.EnterRoomRequestDto;
import com.nextisus.project.mom.room.service.MomRoomService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class MomRoomController {

    private final MomRoomService momRoomService;
    private final AuthUtil authUtil;

    @PostMapping("/enter")
    public SuccessResponse<?> enterRoom(@RequestBody EnterRoomRequestDto dto) {
        long userId = Long.parseLong(authUtil.getCurrentUserId());
        momRoomService.enterRoom(userId, dto);
        return SuccessResponse.empty();
    }

}
