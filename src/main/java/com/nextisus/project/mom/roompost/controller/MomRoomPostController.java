package com.nextisus.project.mom.roompost.controller;

import com.nextisus.project.util.response.PageResponse;
import com.nextisus.project.util.response.SuccessResponse;
import java.awt.print.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomPost")
@RequiredArgsConstructor
public class MomRoomPostController {

    private MomRoomPostService momRoomPostService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<PageResponse<GetRoomPostListResponseDto>> getRoomPostList(
            @PageableDefault(size = 4) Pageable pageable
            ) {
        PageResponse<GetRoomPostListResponseDto> res = momRoomPostService.getRoomPostList(pageable);
        return SuccessResponse.of(res);
    }
}
