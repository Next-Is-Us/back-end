package com.nextisus.project.user.repository;

import com.nextisus.project.condition.exception.ConditionNotFoundException;
import com.nextisus.project.domain.Condition;
import com.nextisus.project.domain.Link;
import com.nextisus.project.domain.User;
import java.util.Optional;

import com.nextisus.project.user.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByLinkAndNickname(Link link, String nickname);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByLink(Link link);
    Optional<User> findByIdAndLink(Long id, Link link);
    default User getByUser(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }

    default User getByNickname(String nickname) {
        return findByNickname(nickname).orElseThrow(UserNotFoundException::new);
    }

}
