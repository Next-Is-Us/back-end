package com.nextisus.project.admin.service;

import com.nextisus.project.admin.dto.CreateAccessTokenRequestDto;
import com.nextisus.project.admin.dto.CreateAccessTokenResponseDto;
import com.nextisus.project.admin.dto.CreateInfoPostRequestDto;
import com.nextisus.project.admin.dto.CreateInfoPostResponseDto;
import com.nextisus.project.domain.InfoPost;
import com.nextisus.project.domain.InfoPostImg;
import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.User;
import com.nextisus.project.exception.infopost.InfoPostInternalServerException;
import com.nextisus.project.image.service.S3UploadService;
import com.nextisus.project.repository.InfoPostImgRepository;
import com.nextisus.project.repository.InfoPostRepository;
import com.nextisus.project.repository.RoleRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.exception.EnumUtils;
import com.nextisus.project.util.jwt.JwtTokenProvider;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final InfoPostRepository infoPostRepository;
    private final S3UploadService s3UploadService;
    private final InfoPostImgRepository infoPostImgRepository;

    @Override
    public CreateAccessTokenResponseDto createAccessToken(CreateAccessTokenRequestDto dto) {

        // 관리자 계정 찾기
        User adminUser = userRepository.getByNickname(dto.getNickname());

        // Role
        List<Role> roles = adminUser.getUserRoles().stream()
                .map(EnumUtils::fromUserRole)
                .map(roleRepository::getByRoleName)
                .collect(Collectors.toList());

        // accessToken 발급
        String accessToken = jwtTokenProvider.createToken(adminUser.getId().toString(), roles);

        // 응답
        return CreateAccessTokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public CreateInfoPostResponseDto createInfoPost(CreateInfoPostRequestDto dto, Long id) {
        try{
            // 관리자 계정 찾기
            User adminUser = userRepository.getByUser(id);
            MultipartFile thumbnail = dto.getImageUrl().get(0);
            String thumbnailPath = s3UploadService.upload(thumbnail,"info-post-thumbnail");

            // InfoPost 생성
            InfoPost infoPost = InfoPost.toEntity(dto, adminUser,thumbnailPath);
            infoPostRepository.save(infoPost);


            //이미지 업로드
            String imgPath = null;
            if(dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
                for(MultipartFile file : dto.getImageUrl()) {
                    imgPath = s3UploadService.upload(file,"info-image");
                    InfoPostImg newImage = InfoPostImg.builder()
                            .infoPost(infoPost)
                            .url(imgPath)
                            .build();
                    newImage.setPost(infoPost);
                    infoPostImgRepository.save(newImage);
                }
            }
            // 응답
            return CreateInfoPostResponseDto.builder()
                    .postId(infoPost.getId())
                    .build();
        }
        catch (DataAccessException dae) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
