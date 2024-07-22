package com.nextisus.project.role.repository;

import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.RoleName;
import com.nextisus.project.role.exception.RoleNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);

    default Role getByRoleName(RoleName roleName) {
        return findByRoleName(roleName).orElseThrow(RoleNotFoundException::new);
    }
}
