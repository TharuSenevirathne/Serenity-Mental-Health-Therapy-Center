package org.example.orm_courseworks.bo.custom;


import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.PaymentDTO;

public interface PaymentBO extends SuperBO {

    PaymentDTO searchPayment(String sessionId);

    boolean pay(String paymentId, String paymentAmount);

    ObservableList<PaymentDTO> getAllPayments(String patientId);
}
