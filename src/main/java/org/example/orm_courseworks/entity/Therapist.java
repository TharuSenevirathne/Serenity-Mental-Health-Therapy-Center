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
@Table(name = "therapist")

public class Therapist implements SuperEntity {
    @Id
    @Column(name = "therapist_id")
    private String therapistId;
    private String therapistName;
    private String specialization;

    @OneToMany(mappedBy = "therapist")
    private List<TherapyProgram>therapyProgram;

    @OneToMany(mappedBy = "therapist")
    private List<TherapySession> therapySession;
}
