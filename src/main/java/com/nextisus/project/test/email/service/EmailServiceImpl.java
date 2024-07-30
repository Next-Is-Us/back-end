package com.nextisus.project.test.email.service;

import com.nextisus.project.test.email.dto.EmailMessageDto;
import com.nextisus.project.test.email.dto.EmailRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Override
    public String sendPlainText(EmailRequestDto dto) {

        // 보낼 이메일 내용 구성
        EmailMessageDto emailMessageDto = EmailMessageDto.builder()
                .to(dto.getEmail())
                .subject("간단한 텍스트 전송 테스트")
                .content("간단한 텍스트입니다.")
                .build();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessageDto.getTo());
            mimeMessageHelper.setSubject(emailMessageDto.getSubject());
            mimeMessageHelper.setText(emailMessageDto.getContent());
            javaMailSender.send(mimeMessage);

            return "메일 전송 성공";

        } catch (MessagingException e) {
            System.out.println(e.getLocalizedMessage());
            return "메일 전송 실패, 서버의 로그를 확인해주세요.";
        }
    }
}
