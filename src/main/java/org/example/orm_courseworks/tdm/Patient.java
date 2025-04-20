package org.example.orm_courseworks.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Patient {
    private String patientId;
    private String patientName;
    private String phone;
    private String gender;
    private String birthDate;
}
