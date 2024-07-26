package com.nextisus.project.all.infopost.dto;

import com.nextisus.project.domain.InfoPost;
import java.time.LocalDateTime;
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
    // TODO: [주연] 이미지 필드 추가

    public static GetDetailInfoPostResDto from(InfoPost infoPost) {
        return new GetDetailInfoPostResDto(
                infoPost.getTitle(),
                infoPost.getContent(),
                infoPost.getCreateAt()
        );
    }
}
