package org.example.orm_courseworks.dao.custom;

import org.example.orm_courseworks.dao.CrudDAO;
import org.example.orm_courseworks.dto.PatientDTO;
import org.example.orm_courseworks.entity.Patient;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PatientDAO extends CrudDAO<Patient,String> {

}
