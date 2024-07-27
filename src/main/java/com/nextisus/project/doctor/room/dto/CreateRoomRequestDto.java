package com.nextisus.project.doctor.room.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateRoomRequestDto {
    private String name;
    private String introduction;
    private Long necessaryNtfCount;
}
