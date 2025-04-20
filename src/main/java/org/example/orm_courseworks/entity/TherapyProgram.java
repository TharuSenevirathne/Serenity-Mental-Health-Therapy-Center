package org.example.orm_courseworks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapy_program")

public class TherapyProgram implements SuperEntity{
    @Id
    @Column(name = "therapyProgram_id")
    private String therapyProgramId;
    private String therapyProgramName;
    private String therapyProgramDuration;
    private double therapyProgramCost;

    @OneToMany(mappedBy = "therapyProgram")
    private List<TherapySession> therapySession;
}
