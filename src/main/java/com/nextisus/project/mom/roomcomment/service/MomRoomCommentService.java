package com.nextisus.project.mom.roomcomment.service;

import com.nextisus.project.mom.roomcomment.dto.CreateCommentRequestDto;
import com.nextisus.project.util.response.SuccessResponse;

public interface MomRoomCommentService {

    SuccessResponse<?> createComment(CreateCommentRequestDto dto, Long userId);
}
