package org.example.orm_courseworks.bo.custom;

import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.PatientDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PatientBO extends SuperBO {
    boolean save(PatientDTO patientDTO);
    Optional<String> getLastPK();
    List<PatientDTO> getAll();
    boolean deleteByPK(String id);
    boolean update(PatientDTO patientDTO);
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Optional<PatientDTO> findByPK(String id);
}
