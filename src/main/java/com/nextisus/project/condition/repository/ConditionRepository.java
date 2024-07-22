package com.nextisus.project.condition.repository;

import com.nextisus.project.domain.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConditionRepository  extends JpaRepository<Condition, Long> {
    Optional<Condition> findByCreateAt(LocalDateTime date);

    Optional<Condition> findByDate(String date);
}


