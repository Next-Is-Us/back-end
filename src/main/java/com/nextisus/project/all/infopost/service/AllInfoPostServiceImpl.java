package com.nextisus.project.all.infopost.service;

import com.nextisus.project.all.infopost.dto.GetDetailInfoPostResDto;
import com.nextisus.project.all.infopost.dto.GetListInfoPostResDto;
import com.nextisus.project.domain.InfoPost;
import com.nextisus.project.repository.InfoPostRepository;
import com.nextisus.project.util.response.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AllInfoPostServiceImpl implements AllInfoPostService {

    private final InfoPostRepository infoPostRepository;

    @Override
    public PageResponse<GetListInfoPostResDto> getListInfoPost(Pageable pageable) {
        Page<GetListInfoPostResDto> posts = infoPostRepository.findAll(pageable).map(GetListInfoPostResDto::from);
        List<GetListInfoPostResDto> list = posts.stream().toList();
        PageImpl<GetListInfoPostResDto> data = new PageImpl<>(list, pageable,
                posts.getTotalElements());
        return PageResponse.of(data);
    }

    @Override
    public GetDetailInfoPostResDto getDetailInfoPost(Long infoPostId) {
        InfoPost infoPost = infoPostRepository.getByInfoPostId(infoPostId);
        return GetDetailInfoPostResDto.from(infoPost);
    }
}
