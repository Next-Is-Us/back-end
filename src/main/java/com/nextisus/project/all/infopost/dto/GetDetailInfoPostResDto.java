package com.nextisus.project.all.infopost.dto;

import com.nextisus.project.domain.InfoPost;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetDetailInfoPostResDto {
    private String title;
    private String content;
    private LocalDateTime createdAt; // TODO: [민서] Figma 디자인 나오면 데이터 가공 필요
    private List<String> imageUrl;

    public static GetDetailInfoPostResDto from(InfoPost infoPost, List<String> imageUrl) {
        return new GetDetailInfoPostResDto(
                infoPost.getTitle(),
                infoPost.getContent(),
                infoPost.getCreateAt(),
                imageUrl
        );
    }
}
