package com.nextisus.project.mom.roomcomment.service;

import com.nextisus.project.domain.RoomComment;
import com.nextisus.project.domain.RoomPost;
import com.nextisus.project.domain.User;
import com.nextisus.project.mom.roomcomment.dto.CreateCommentRequestDto;
import com.nextisus.project.repository.RoomCommentRepository;
import com.nextisus.project.repository.RoomPostRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomRoomCommentServiceImpl implements MomRoomCommentService {

    private final UserRepository userRepository;
    private final RoomCommentRepository roomCommentRepository;
    private final RoomPostRepository roomPostRepository;

    @Override
    public SuccessResponse<?> createComment(CreateCommentRequestDto dto, Long userId) {

        User user = userRepository.getByUser(userId);
        RoomPost roomdPost = roomPostRepository.getById(dto.getRoomPostId());

        RoomComment roomComment = RoomComment.builder()
                .commentContent(dto.getCommentContent())
                .user(user)
                .roomPost(roomdPost)
                .build();

        roomComment.setUser(user,roomdPost);

        roomCommentRepository.save(roomComment);

        return null;
    }
}
