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
@Table(name = "program")

public class Program {
    @Id
    private String programId;
    private String name;
    private String duration;
    private String fee;

    @OneToMany(mappedBy = "program")
    private List<TherapyProgram> therapyProgram;

    @OneToMany(mappedBy = "programs")
    private List<TherapySession> therapySession;
}
