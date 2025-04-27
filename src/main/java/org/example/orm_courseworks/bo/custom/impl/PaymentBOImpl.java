package org.example.orm_courseworks.bo.custom.impl;


import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.custom.PaymentBO;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.PaymentDAO;
import org.example.orm_courseworks.dto.PaymentDTO;
import org.example.orm_courseworks.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);


    @Override
    public PaymentDTO searchPayment(String sessionId) {
        Payment payment = paymentDAO.search(sessionId);
        if (payment == null) {
            return null;
        } else {
            return new PaymentDTO(payment.getPaymentId(),payment.getPaymentDetails(),payment.getFullAmount(),payment.getRemainingAmount());
        }
    }

    @Override
    public boolean pay(String paymentId, String payingAmount) {
        return paymentDAO.pay(paymentId,payingAmount);
    }

    @Override
    public ObservableList<PaymentDTO> getAllPayments(String patientId) {
        List<Payment> payments = paymentDAO.getAllPatients(patientId);
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(payment.getPaymentId(),payment.getPaymentDetails(),payment.getFullAmount(),payment.getRemainingAmount()));
        }
        return (ObservableList<PaymentDTO>) paymentDTOS;
    }
}
