package org.example.orm_courseworks.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentDTO {
    private String paymentId;
    private double amount;
    private double fullamount;
    private double remainingamount;
    private String date;
}
