package com.nextisus.project.link.repository;

import com.nextisus.project.domain.Link;
import com.nextisus.project.link.exception.LinkNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByLink(String link);

    default Link getByLink(String link) {
        return findByLink(link).orElseThrow(LinkNotFoundException::new);
    }

}
