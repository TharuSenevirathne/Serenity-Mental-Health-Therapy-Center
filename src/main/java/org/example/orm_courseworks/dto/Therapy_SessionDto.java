package org.example.orm_courseworks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Therapy_SessionDto {
    private String patientId;
    private String programId;
    private String therapistId;
    private String sessionDate;
}
