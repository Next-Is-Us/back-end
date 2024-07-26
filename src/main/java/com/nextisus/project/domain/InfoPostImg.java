package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="INFO_POST_IMG")
public class InfoPostImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long imageId;

    @Column(name="URL")
    private String url;

    @ManyToOne
    @JoinColumn
    private InfoPost post;

    public void setPost(InfoPost post) {
        this.post = post;
    }
}
