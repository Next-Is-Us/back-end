package com.nextisus.project.doctor.room.controller;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import com.nextisus.project.doctor.room.dto.CreateRoomResponseDto;
import com.nextisus.project.doctor.room.service.DoctorRoomService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
@PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMIN')")
public class DoctorRoomController {

    private final AuthUtil authUtil;
    private final DoctorRoomService doctorRoomService;

    @PostMapping("/createRoom")
    public SuccessResponse<CreateRoomResponseDto> createRoom(@RequestBody CreateRoomRequestDto dto) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        CreateRoomResponseDto res = doctorRoomService.createRoom(dto, userId);
        return SuccessResponse.of(res);
    }
}
