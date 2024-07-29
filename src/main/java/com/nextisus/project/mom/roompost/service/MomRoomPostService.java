package com.nextisus.project.mom.roompost.service;

import com.nextisus.project.util.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface MomRoomPostService {
    PageResponse<GetRoomPostListRequestDto> getRoomPostList(Pageable pageable);
}
