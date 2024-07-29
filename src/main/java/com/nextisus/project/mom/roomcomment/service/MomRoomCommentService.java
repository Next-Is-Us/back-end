package com.nextisus.project.mom.roomcomment.service;

import com.nextisus.project.mom.roomcomment.dto.CreateCommentRequestDto;
import com.nextisus.project.mom.roomcomment.dto.RoomCommentListDto;
import com.nextisus.project.util.response.SuccessResponse;

import java.util.List;

public interface MomRoomCommentService {

    SuccessResponse<?> createComment(CreateCommentRequestDto dto, Long userId);

    List<RoomCommentListDto> getComments(Long roomPostId);
}
