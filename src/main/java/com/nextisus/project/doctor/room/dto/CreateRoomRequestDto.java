package com.nextisus.project.doctor.room.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class CreateRoomRequestDto {
    private String name;
    private String introduction;
    private Long necessaryNftCount;
    private MultipartFile thumbnail;
}
