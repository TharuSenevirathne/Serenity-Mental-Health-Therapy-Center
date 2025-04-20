package org.example.orm_courseworks.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TherapistDTO {
    private String therapistId;
    private String therapistName;
    private String specialization;
}
