package com.nextisus.project.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateInfoPostRequestDto {
    private String title;
    private String content;
}
