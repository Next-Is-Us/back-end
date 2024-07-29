package com.nextisus.project.doctor.roompost.controller;

import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomPost")
@RequiredArgsConstructor
public class DoctorRoomPostController {

    private final AuthUtil authUtil;
    private final DoctorRoomPostService doctorRoomPostService;

    @PostMapping("/{roomId}")
    public SuccessResponse<CreateRoomPostResponseDto> createRoomPost(@RequestBody CreateRoomPostRequestDto dto) {
        long userId = Long.parseLong(authUtil.getCurrentUserId());
        CreateRoomPostResponseDto res = doctorRoomPostService.createRoomPost(userId, dto);
        return SuccessResponse.of(res);
    }
}
