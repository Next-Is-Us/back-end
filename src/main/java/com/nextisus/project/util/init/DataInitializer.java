package com.nextisus.project.util.init;

import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.RoleName;
import com.nextisus.project.role.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(1L, RoleName.ROLE_ADMIN));
            roleRepository.save(new Role(2L, RoleName.ROLE_MOM));
            roleRepository.save(new Role(3L, RoleName.ROLE_CHILD));
            roleRepository.save(new Role(4L, RoleName.ROLE_DOCTOR));
        }
    }
}
