package com.nextisus.project.client.mypage.dto;

import com.nextisus.project.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetMyFamilyInformationResponseDto {
    private String roleName;
    private String nickname;

    public static GetMyFamilyInformationResponseDto from(User user) {
        return GetMyFamilyInformationResponseDto.builder()
                .nickname(user.getNickname())
                .roleName(null)
                .build();
    }
}
