package com.nextisus.project.all.infopost.controller;

import com.nextisus.project.all.infopost.dto.GetDetailInfoPostResDto;
import com.nextisus.project.all.infopost.service.AllInfoPostService;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/infoPost")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_MOM', 'ROLE_SON', 'ROLE_DAUGHTER')")
public class AllInfoPostController {

    private final AllInfoPostService allInfoPostService;

    @GetMapping("/{infoPostId}")
    public SuccessResponse<GetDetailInfoPostResDto> getDetailInfoPost(@PathVariable("infoPostId") Long infoPostId) {
        GetDetailInfoPostResDto res = allInfoPostService.getDetailInfoPost(infoPostId);
        return SuccessResponse.of(res);
    }

}
