package com.nextisus.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_ROOMS")
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public UserRoom(User user, Room room) {
        this.user = user;
        this.room = room;
        user.addUserRoom(this); // 양방향 관계 설정
        room.addUserRoom(this); // 양방향 관계 설정
    }

    public void setUser(User user) {
        this.user = user;
        if (user.getUserRooms() == null) {
            user.setUserRooms(new ArrayList<>());
        }
        user.getUserRooms().add(this);
    }

    public void setRoom(Room room) {
        this.room = room;
        if (room.getUserRooms() == null) {
            room.setUserRooms(new ArrayList<>());
        }
        room.getUserRooms().add(this);
    }
}
