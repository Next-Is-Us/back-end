package com.nextisus.project.mom.roomcomment.dto;

import com.nextisus.project.domain.RoomComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomCommentListDto {
    private Long commentId;
    private String commentContent;
    private String author;

    public static RoomCommentListDto from(RoomComment roomComment) {
        return new RoomCommentListDto(
                roomComment.getCommentId(),
                roomComment.getCommentContent(),
                roomComment.getUser().getNickname()
        );
    }

}
