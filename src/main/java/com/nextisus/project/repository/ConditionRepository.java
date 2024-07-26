package com.nextisus.project.repository;

import com.nextisus.project.exception.condition.UserConditionNotFoundException;
import com.nextisus.project.domain.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionRepository  extends JpaRepository<Condition, Long> {
    List<Condition> findByYear(Long year);
    List<Condition> findAllByUser_Id(Long userId);

    default List<Condition> getAllById(Long userId) {
        List<Condition> conditions = findAllByUser_Id(userId);
        if(conditions.isEmpty()) {
            throw new UserConditionNotFoundException();
        }
        return conditions;
    }
    List<Condition> findAllByNftIsNull();
    List<Condition> findAllByNft_NftId(Long nftId);
    Long countByUser_Id(Long userId);
}


