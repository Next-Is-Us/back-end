package com.nextisus.project.client.healthrecord.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePdfDto {
    @NotNull(message = "pdf 파일을 넣어주세요")
    MultipartFile pdfFile;
}
