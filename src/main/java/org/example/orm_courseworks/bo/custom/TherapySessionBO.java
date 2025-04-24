package org.example.orm_courseworks.bo.custom;

import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.PaymentDTO;
import org.example.orm_courseworks.dto.ProgramHistoryDTO;
import org.example.orm_courseworks.dto.TherapySessionDTO;

import java.sql.SQLException;
import java.util.List;

public interface TherapySessionBO extends SuperBO {
    boolean addSession(TherapySessionDTO therapySessionDTO, PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    String search(String programId);
    boolean updateSession(TherapySessionDTO therapySessionDTO) throws SQLException, ClassNotFoundException;
    boolean deletePatient(String patientId) throws SQLException, ClassNotFoundException;
    List<String> getProgramIds(String patientId);
    List<ProgramHistoryDTO> getPatientTherapyHistory(String patientId);
    List<String> getPatientIdsFromTherapySessions();

}
