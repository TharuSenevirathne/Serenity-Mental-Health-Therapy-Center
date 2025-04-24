package org.example.orm_courseworks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")

public class Payment implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private String paymentId;
    private double amount;
    private double fullamount;
    private double remainingamount;
    private String date;

    @OneToOne
    @JoinColumn(name = "session_id")
    private TherapySession therapySession;
}
