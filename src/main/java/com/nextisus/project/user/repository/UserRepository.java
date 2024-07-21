package com.nextisus.project.user.repository;

import com.nextisus.project.domain.Link;
import com.nextisus.project.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByLink(Link link);
    Optional<User> findByIdAndLink(Long id, Link link);
}
