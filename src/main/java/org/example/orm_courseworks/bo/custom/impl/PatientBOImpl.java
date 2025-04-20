package org.example.orm_courseworks.bo.custom.impl;

import org.example.orm_courseworks.bo.custom.PatientBO;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.impl.PatientDAOImpl;
import org.example.orm_courseworks.dto.PatientDTO;
import org.example.orm_courseworks.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientBOImpl implements PatientBO {

    private final PatientDAOImpl patientDAO = (PatientDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public boolean save(PatientDTO patientDTO) {
        Patient patient = toEntity(patientDTO);
        return patientDAO.save(patient);
    }

    @Override
    public Optional<String> getLastPK() {
        return patientDAO.getLastPK();
    }

    @Override
    public List<PatientDTO> getAll() {
        List<PatientDTO> users = new ArrayList<>();
        List<Patient> all = patientDAO.getAll();
        for (Patient patient : all) {
            users.add(new PatientDTO(
                    patient.getPatientId(),
                    patient.getPatientName(),
                    patient.getPhone(),
                    patient.getGender(),
                    patient.getBirthDate()
            ));
        }
        return users;
    }

    @Override
    public boolean deleteByPK(String id) {
        return patientDAO.deleteByPK(id);
    }

    @Override
    public boolean update(PatientDTO patientDTO) {
        Patient patient = toEntity(patientDTO);
        return patientDAO.update(patient);
    }

    public static PatientDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }
        return new PatientDTO(
                patient.getPatientId(),
                patient.getPatientName(),
                patient.getPhone(),
                patient.getGender(),
                patient.getBirthDate()
        );
    }

    public static Patient toEntity(PatientDTO patientDTO) {
        if (patientDTO == null) {
            return null;
        }
        return new Patient(
                patientDTO.getPatientId(),
                patientDTO.getPatientName(),
                patientDTO.getPhone(),
                patientDTO.getGender(),
                patientDTO.getBirthDate(),
                null
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<PatientDTO> findByPK(String id) {
        return Optional.empty();
    }

}
