package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NFT")
public class Nft extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nftId; // Long 타입으로 변경

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name = "RECORD_PERIOD")
    private String recordPeriod;

    public void createNft (User user) {
        this.user = user;
    }

}
