package com.nextisus.project.condition.repository;

import com.nextisus.project.condition.exception.UserConditionNotFoundException;
import com.nextisus.project.domain.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConditionRepository  extends JpaRepository<Condition, Long> {
    List<Condition> findByYear(Long year);
    List<Condition> findAllByUser_Id(Long id);

    default List<Condition> getAllById(Long id) {
        List<Condition> conditions = findAllByUser_Id(id);
        if(conditions.isEmpty()) {
            throw new UserConditionNotFoundException();
        }
        return conditions;
    }
}


