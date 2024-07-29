package com.nextisus.project.mom.roompost.service;

import com.nextisus.project.repository.RoomPostRepository;
import com.nextisus.project.util.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomRoomPostServiceImpl implements MomRoomPostService {

    private final RoomPostRepository roomPostRepository;

    @Override
    public PageResponse<GetRoomPostListRequestDto> getRoomPostList(Pageable pageable) {
        return null;
    }
}
