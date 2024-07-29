package com.nextisus.project.client.link.controller;

import com.nextisus.project.client.link.dto.LinkResponseDto;
import com.nextisus.project.client.link.service.LinkService;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/link")
public class LinkController{

    private final LinkService linkService;

    /**
     * UUID 생성
     */
    @PostMapping
    public SuccessResponse<LinkResponseDto> link() {
        LinkResponseDto res = linkService.link();
        return SuccessResponse.of(res);
    }
}
