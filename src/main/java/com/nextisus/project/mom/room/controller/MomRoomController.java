package com.nextisus.project.mom.room.controller;

import com.nextisus.project.mom.room.service.MomRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class MomRoomController {

    private final MomRoomService momRoomService;

}
