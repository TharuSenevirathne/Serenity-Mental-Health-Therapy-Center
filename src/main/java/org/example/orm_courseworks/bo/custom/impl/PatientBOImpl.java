package org.example.orm_courseworks.bo.custom.impl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.custom.PatientBO;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.PatientDAO;
import org.example.orm_courseworks.dto.PatientDto;
import org.example.orm_courseworks.dto.UserDto;
import org.example.orm_courseworks.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
    @Override
    public ObservableList<PatientDto> getAllPatients() throws SQLException, ClassNotFoundException {
        List<PatientDto> patientDtos = new ArrayList<>();
        List<Patient> patients = patientDAO.getAll();
        for (Patient patient : patients){
            patientDtos.add(new PatientDto(patient.getId(),patient.getName(),patient.getEmail(),patient.getAddress(),patient.getTel(),patient.getRegisterDate()));
        }
        return FXCollections.observableArrayList(patientDtos);
    }

    @Override
    public boolean addPatient(PatientDto patientDto, UserDto userDto) throws SQLException, ClassNotFoundException {
        return patientDAO.save(new Patient(patientDto.getId(),patientDto.getName(),patientDto.getEmail(),patientDto.getAddress(),patientDto.getTel(),patientDto.getRegisterDate()));
    }

    @Override
    public boolean updatePatient(PatientDto patientDto) throws SQLException, ClassNotFoundException {
        return patientDAO.update(new Patient(patientDto.getId(),patientDto.getName(),patientDto.getEmail(),patientDto.getAddress(),patientDto.getTel(),patientDto.getRegisterDate()));
    }

    @Override
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException {
        return patientDAO.delete(id);
    }

    @Override
    public PatientDto searchPatient(String id) {
        Patient patient = patientDAO.search(id);

        if (patient == null){
            return null;
        }else {
            return new PatientDto(patient.getId(),patient.getName(),patient.getEmail(),patient.getAddress(),patient.getTel(),patient.getRegisterDate());
        }
    }

}
