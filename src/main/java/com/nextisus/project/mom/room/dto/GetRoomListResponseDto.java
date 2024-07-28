package com.nextisus.project.mom.room.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetRoomListResponseDto {
    private Long roomId;
    private String name;
    private Long peopleCount;
    private Long necessaryNftCount;
    private Boolean isPossibleToEnter;
    private String thumbnail;
}
