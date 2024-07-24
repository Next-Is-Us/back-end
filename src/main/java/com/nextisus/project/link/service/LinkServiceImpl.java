package com.nextisus.project.link.service;

import com.nextisus.project.domain.Link;
import com.nextisus.project.repository.LinkRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService{

    private final LinkRepository linkRepository;

    @Override
    public String link() {

        UUID url = UUID.randomUUID();
        log.info(String.valueOf(url));

        Link save = linkRepository.save(Link.toEntity(url.toString()));
        return save.getLink();
    }

}
