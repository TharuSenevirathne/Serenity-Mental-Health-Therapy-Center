package org.example.orm_courseworks.bo.custom;

import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.PatientDto;
import org.example.orm_courseworks.dto.UserDto;

import java.sql.SQLException;

public interface PatientBO extends SuperBO {
    ObservableList<PatientDto> getAllPatients() throws SQLException, ClassNotFoundException;

    boolean addPatient(PatientDto patientDto, UserDto userDto) throws SQLException, ClassNotFoundException;

    boolean updatePatient(PatientDto patientDto) throws SQLException, ClassNotFoundException;

    boolean deletePatient(String id) throws SQLException, ClassNotFoundException;

    PatientDto searchPatient(String id);

}
