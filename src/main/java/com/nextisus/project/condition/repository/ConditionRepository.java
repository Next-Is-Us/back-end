package com.nextisus.project.condition.repository;

import com.nextisus.project.domain.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository  extends JpaRepository<Condition, Long> {
}
