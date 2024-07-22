package com.nextisus.project.condition.repository;

import com.nextisus.project.condition.exception.ConditionNotFoundException;
import com.nextisus.project.domain.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConditionRepository  extends JpaRepository<Condition, Long> {
/*
    Optional<Condition> findByUser(Long userId);

    default Condition getByUser(Long userId) {
        return findByUser(userId).orElseThrow(ConditionNotFoundException::new);
    }*/

    List<Condition> findByYear(Long year);
}


