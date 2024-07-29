package com.nextisus.project.mom.roomcomment.controller;

import com.nextisus.project.mom.roomcomment.dto.CreateCommentRequestDto;
import com.nextisus.project.mom.roomcomment.service.MomRoomCommentService;
import com.nextisus.project.mom.roomcomment.service.MomRoomCommentServiceImpl;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomComment")
@RequiredArgsConstructor
public class MomRoomCommentController {

    private final AuthUtil authUtil;
    private final MomRoomCommentService momRoomCommentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<?> createComment(@RequestBody @Valid CreateCommentRequestDto dto) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        momRoomCommentService.createComment(dto,userId);
        return SuccessResponse.empty();
    }
}
