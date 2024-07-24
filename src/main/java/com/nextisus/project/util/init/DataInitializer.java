package com.nextisus.project.util.init;

import static com.nextisus.project.domain.RoleName.ROLE_ADMIN;

import com.nextisus.project.domain.Role;
import com.nextisus.project.domain.RoleName;
import com.nextisus.project.domain.User;
import com.nextisus.project.domain.UserRole;
import com.nextisus.project.role.repository.RoleRepository;
import com.nextisus.project.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        createRole();
        createAdminAccount();
    }

    // 역할 생성
    private void createRole() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(1L, ROLE_ADMIN));
            roleRepository.save(new Role(2L, RoleName.ROLE_MOM));
            roleRepository.save(new Role(3L, RoleName.ROLE_SON));
            roleRepository.save(new Role(4L, RoleName.ROLE_DAUGHTER));
            roleRepository.save(new Role(5L, RoleName.ROLE_DOCTOR));
        }
    }

    // 관리자 계정 생성
    private void createAdminAccount() {
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.getByRoleName(RoleName.ROLE_ADMIN);

            // User 생성
            User adminUser = User.builder()
                    .nickname("관리자")
                    .isNotificationEnabled(false)
                    .build();

            // UserRole 생성
            UserRole adminUserRole = UserRole.builder()
                    .user(adminUser)
                    .role(adminRole)
                    .build();

            // UserRole 설정
            adminUser.addUserRole(adminUserRole);

            // User 저장
            userRepository.save(adminUser);
        }
    }
}
