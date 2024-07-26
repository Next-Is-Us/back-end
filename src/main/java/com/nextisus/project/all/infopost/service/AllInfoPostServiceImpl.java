package com.nextisus.project.all.infopost.service;

import com.nextisus.project.all.infopost.dto.GetDetailInfoPostResDto;
import com.nextisus.project.domain.InfoPost;
import com.nextisus.project.repository.InfoPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AllInfoPostServiceImpl implements AllInfoPostService {

    private final InfoPostRepository infoPostRepository;

    @Override
    public GetDetailInfoPostResDto getDetailInfoPost(Long infoPostId) {
        InfoPost infoPost = infoPostRepository.getByInfoPostId(infoPostId);
        return GetDetailInfoPostResDto.from(infoPost);
    }
}
