package com.nextisus.project.domain;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROOMS")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "people_count")
    @Builder.Default
    private Long peopleCount = 1L; // 소통방 생성 시 전문의가 무조건 존재하기 때문

    @Column(name = "necessary_nft_count")
    @Builder.Default
    private Long necessaryNftCount = 0L;

    @Setter
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRoom> userRooms = new ArrayList<>();

    public static Room toEntity(CreateRoomRequestDto dto) {
        return Room.builder()
                .name(dto.getName())
                .introduction(dto.getIntroduction())
                .necessaryNftCount(dto.getNecessaryNftCount())
                .build();
    }

    public void addUserRoom(UserRoom userRoom) {
        if (userRooms == null) {
            userRooms = new ArrayList<>();
        }
        this.userRooms.add(userRoom);
        userRoom.setRoom(this); // 양방향 관계 설정
    }
}
