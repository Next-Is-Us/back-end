package com.nextisus.project.link.controller;

import com.nextisus.project.link.service.LinkService;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/link")
public class LinkController {

    private final LinkService linkService;

    /**
     * UUID 생성
     */
    @PostMapping
    public SuccessResponse<String> link() {
        String url = linkService.link();
        return SuccessResponse.of(url);
    }
}
