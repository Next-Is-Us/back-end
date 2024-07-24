package com.nextisus.project.infopost.repository;

import com.nextisus.project.domain.InfoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPostRepository extends JpaRepository<InfoPost, Long> {
}
