package org.example.orm_courseworks.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TherapySession {
    private String therapySessionId;
    private String date;
    private String time;
    private String status;
}
