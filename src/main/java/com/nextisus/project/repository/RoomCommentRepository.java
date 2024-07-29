package com.nextisus.project.repository;

import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.RoomComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomCommentRepository extends JpaRepository<RoomComment, Long> {
    List<RoomComment> findAllByRoomPost_Id(Long roomPostId);
    Long CountByRoomPost_Id(Long roomPostId);
}
