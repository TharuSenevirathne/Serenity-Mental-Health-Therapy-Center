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
@Table(name = "patient")
public class Patient implements SuperEntity{
    @Id
    @Column(name = "patient_id")
    private String patientId;
    private String patientName;
    private String phone;
    private String gender;
    private String birthDate;

    @OneToMany(mappedBy = "patient")
    private List<TherapySession> therapySession;
}
