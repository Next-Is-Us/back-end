package com.nextisus.project.repository;

import com.nextisus.project.domain.Room;
import com.nextisus.project.exception.room.RoomNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findById(Long id);

    default Room getById(Long id) {
        return findById(id).orElseThrow(RoomNotFoundException::new);
    }
}
