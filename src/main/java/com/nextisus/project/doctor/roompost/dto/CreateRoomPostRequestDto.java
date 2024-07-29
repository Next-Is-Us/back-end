package com.nextisus.project.doctor.roompost.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomPostRequestDto {
    private Long roomId;
    private String title;
    private String content;
}
