package org.example.orm_courseworks.bo.custom;

import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.PatientDTO;
import org.example.orm_courseworks.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PatientBO extends SuperBO {
    ObservableList<PatientDTO> getAllPatients()throws SQLException, ClassNotFoundException;
    boolean addPatient(PatientDTO patientDTO , UserDTO userDTO)throws SQLException, ClassNotFoundException;
    boolean updatePatient(PatientDTO patientDTO)throws SQLException, ClassNotFoundException;
    boolean deletePatient(String id)throws SQLException, ClassNotFoundException;

    PatientDTO searchPatient(String patientId);
}
