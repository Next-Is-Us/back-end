package com.nextisus.project.mom.roomcomment.service;

import com.nextisus.project.domain.RoomComment;
import com.nextisus.project.domain.User;
import com.nextisus.project.mom.roomcomment.dto.CreateCommentRequestDto;
import com.nextisus.project.repository.RoomCommentRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.response.SuccessResponse;
import org.springframework.stereotype.Service;

@Service
public class MomRoomCommentServiceImpl implements MomRoomCommentService {

    private final UserRepository userRepository;
    private final RoomCommentRepository roomCommentRepository;

    public MomRoomCommentServiceImpl(UserRepository userRepository, RoomCommentRepository roomCommentRepository) {
        this.userRepository = userRepository;
        this.roomCommentRepository = roomCommentRepository;
    }

    @Override
    public SuccessResponse<?> createComment(CreateCommentRequestDto dto, Long userId) {

        User byUser = userRepository.getByUser(userId);

        RoomComment roomComment = RoomComment.builder()
                .commentContent(dto.getCommentContent())
                .user(byUser)
                .build();

        roomComment.setUser(byUser);

        roomCommentRepository.save(roomComment);

        return null;
    }
}
