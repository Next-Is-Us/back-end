package com.nextisus.project.test.email.controller;

import com.nextisus.project.test.email.dto.EmailRequestDto;
import com.nextisus.project.test.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    // 텍스트 보내는 API
    @PostMapping("/text")
    public ResponseEntity<String> sendText(@RequestBody EmailRequestDto dto) {
        String responseBody = emailService.sendPlainText(dto);
        return ResponseEntity.ok(responseBody);
    }
}
