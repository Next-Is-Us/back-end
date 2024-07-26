package com.nextisus.project.client.hospital.dto;

import com.nextisus.project.domain.Hospital;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalListResponseDto {
    String HospitalName ;
    String HospitalAddress ;
    String HospitalTel ;
    public static HospitalListResponseDto from(Hospital hospital) {
        return new HospitalListResponseDto(
                hospital.getHospitalName(),
                hospital.getHospitalAddress(),
                hospital.getHospitalTel()
        );
    }
}
