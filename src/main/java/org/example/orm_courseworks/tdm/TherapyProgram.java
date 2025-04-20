package org.example.orm_courseworks.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TherapyProgram {
    private String therapyProgramId;
    private String therapyProgramName;
    private String therapyProgramDuration;
    private double therapyProgramCost;
}
