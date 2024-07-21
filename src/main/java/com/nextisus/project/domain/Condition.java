package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`CONDITION`")
public class Condition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conditionId;

    @Column(name = "SLEEP_TIME")
    private String sleepTime;

    @Column(name = "RECORD", columnDefinition = "TEXT")
    private String record;

    //안면 홍조
    @Column(name="BLUSH")
    private Boolean blush;

    //두통
    @Column(name="HEADACHE")
    private Boolean headache;

    //복통
    @Column(name="STOMACHACHE")
    private Boolean stomachache;

    //변비
    @Column(name="CONSTIPATION")
    private Boolean constipation;

    //근육통
    @Column(name="MUSCLE")
    private Boolean muscle;

    //피부 트러블
    @Column(name="PIMPLE")
    private Boolean pimple;

    //손발 저림
    @Column(name="NUMBNESS")
    private Boolean numbness;

    //오한
    @Column(name="CHILL")
    private Boolean chill;
}
