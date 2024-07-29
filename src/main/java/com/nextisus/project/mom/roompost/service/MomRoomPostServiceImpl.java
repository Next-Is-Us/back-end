package com.nextisus.project.mom.roompost.service;

import com.nextisus.project.domain.RoomPost;
import com.nextisus.project.mom.roompost.dto.GetRoomPostDetailResponseDto;
import com.nextisus.project.mom.roompost.dto.GetRoomPostListResponseDto;
import com.nextisus.project.repository.RoomPostRepository;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.util.response.PageResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomRoomPostServiceImpl implements MomRoomPostService {

    private final RoomRepository roomRepository;
    private final RoomPostRepository roomPostRepository;

    @Override
    public PageResponse<GetRoomPostListResponseDto> getRoomPostList(Long roomId, Pageable pageable) {
        roomRepository.getById(roomId);
        Page<GetRoomPostListResponseDto> roomPosts = roomPostRepository.findAllByRoomIdOrderByCreateAtDesc(roomId, pageable).map(GetRoomPostListResponseDto::from);
        List<GetRoomPostListResponseDto> list = roomPosts.getContent();
        PageImpl<GetRoomPostListResponseDto> data = new PageImpl<>(list, pageable, roomPosts.getTotalElements());
        return PageResponse.of(data);
    }

    @Override
    public GetRoomPostDetailResponseDto getRoomPostDetail(Long userId, Long roomPostId) {
        RoomPost roomPost = roomPostRepository.getById(roomPostId);
        roomPost.execute();
        return GetRoomPostDetailResponseDto.from(roomPost);
    }
}
