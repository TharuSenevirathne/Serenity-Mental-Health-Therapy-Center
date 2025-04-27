package org.example.orm_courseworks.dao.custom;


import org.example.orm_courseworks.dao.CrudDAO;
import org.example.orm_courseworks.entity.Patient;

public interface PatientDAO extends CrudDAO<Patient> {
    Patient search(String id);
}
