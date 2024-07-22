package com.nextisus.project.domain;

import com.nextisus.project.user.dto.SignUpRequestDto;
import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Builder.Default
    @Column(name = "is_notification_enabled")
    private Boolean isNotificationEnabled = true;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserRole> userRoles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_id")
    private Link link;

    public static User toEntity(SignUpRequestDto dto, List<Role> roles, Link link) {

        User user = User.builder()
                .nickname(dto.getNickname())
                .link(link)
                .build();

        List<UserRole> userRoles = dto.getUserRoles().stream()
                .map(roleName -> roles.stream()
                        .filter(role -> role.getRoleName().name().equals(roleName))
                        .findFirst()
                        .map(role -> new UserRole(null, user, role))
                        .orElseThrow(() -> new IllegalArgumentException(roleName + "는 존재하지 않는 역할입니다.")))
                .collect(Collectors.toList());

        user.userRoles = userRoles;

        return user;
    }
}
