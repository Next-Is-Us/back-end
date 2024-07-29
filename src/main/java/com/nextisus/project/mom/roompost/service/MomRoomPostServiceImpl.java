package com.nextisus.project.mom.roompost.service;

import com.nextisus.project.all.infopost.dto.GetListInfoPostResDto;
import com.nextisus.project.mom.roompost.dto.GetRoomPostListResponseDto;
import com.nextisus.project.repository.RoomPostRepository;
import com.nextisus.project.util.response.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomRoomPostServiceImpl implements MomRoomPostService {

    private final RoomPostRepository roomPostRepository;

    @Override
    public PageResponse<GetRoomPostListResponseDto> getRoomPostList(Pageable pageable) {
        Page<GetRoomPostListResponseDto> roomPosts = roomPostRepository.findAllByOrderByCreateAtDesc(pageable).map(GetRoomPostListResponseDto::from);
        List<GetRoomPostListResponseDto> list = roomPosts.getContent();
        PageImpl<GetRoomPostListResponseDto> data = new PageImpl<>(list, pageable, roomPosts.getTotalElements());
        return PageResponse.of(data);
    }
}
