package com.nextisus.project.repository;

import com.nextisus.project.domain.InfoPostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPostImgRepository extends JpaRepository<InfoPostImg,Long> {
}
