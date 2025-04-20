package org.example.orm_courseworks.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TherapyProgramDTO {
    private String therapyProgramId;
    private String therapyProgramName;
    private String therapyProgramDuration;
    private double therapyProgramCost;
}
