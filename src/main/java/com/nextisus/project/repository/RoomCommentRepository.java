package com.nextisus.project.repository;

import com.nextisus.project.domain.RoomComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCommentRepository extends CrudRepository<RoomComment, Long> {
}
