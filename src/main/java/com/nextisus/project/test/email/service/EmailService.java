package com.nextisus.project.test.email.service;


import com.nextisus.project.test.email.dto.EmailRequestDto;

public interface EmailService {
    String sendPlainText(EmailRequestDto dto);
}
