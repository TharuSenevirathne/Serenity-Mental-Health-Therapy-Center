package org.example.orm_courseworks.dao.custom;



import org.example.orm_courseworks.dao.CrudDAO;
import org.example.orm_courseworks.entity.Payment;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {

    Payment search(String sessionId);

    boolean pay(String paymentId, String paymentAmount);

    List<Payment> getAllPatients(String patientId);
}
