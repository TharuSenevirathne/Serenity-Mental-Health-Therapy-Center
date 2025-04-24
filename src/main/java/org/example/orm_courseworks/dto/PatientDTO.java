package org.example.orm_courseworks.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PatientDTO {
    private String patientId;
    private String patientName;
    private String phone;
    private String gender;
    private String regDate;

}
