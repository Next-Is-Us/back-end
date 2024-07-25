package com.nextisus.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="HOSPITAL")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long HospitalId;

    @Column(name="HOSPITAL_NAME")
    private String HospitalName;

    @Column(name="HOSPITSL_ADDRESS")
    private String HospitalAddress;

    @Column(name="HOSPITAL_NUMBER")
    private String HospitalNumber;
}
