package org.example.orm_courseworks.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TherapySessionDTO {
    private String therapySessionId;
    private String date;
    private String time;
    private String status;
}
