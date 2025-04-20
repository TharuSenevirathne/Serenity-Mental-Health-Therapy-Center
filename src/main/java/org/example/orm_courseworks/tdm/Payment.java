package org.example.orm_courseworks.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Payment {
    private String paymentId;
    private double amount;
    private String date;
}
