package com.nextisus.project.all.infopost.dto;

import com.nextisus.project.domain.InfoPost;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetListInfoPostResDto {
    private Long infoPostId;
    private String title;
    private String content;
    // TODO: [주연] 이미지 필드 추가 필요

    private static GetListInfoPostResDto fromInfoPost(InfoPost infoPost) {
        return GetListInfoPostResDto.builder()
                .infoPostId(infoPost.getId())
                .title(infoPost.getTitle())
                .content(infoPost.getContent())
                .build();
    }

    public static GetListInfoPostResDto from(InfoPost infoPost) {
        return fromInfoPost(infoPost);
    }
}
