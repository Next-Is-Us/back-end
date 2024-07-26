package com.nextisus.project.all.infopost.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetDetailInfoPostResDto {
    private String title;
    private String content;
}
