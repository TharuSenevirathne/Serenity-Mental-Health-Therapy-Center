package org.example.orm_courseworks.dao.custom;


import org.example.orm_courseworks.dao.CrudDAO;
import org.example.orm_courseworks.entity.Therapy_Session;

import java.util.List;

public interface SessionDAO extends CrudDAO<Therapy_Session> {

    String search(String programId);

    List<String> getProgramIds(String patientId);

    Long searchSessionId(String patiendId, String programId);

    List<Object[]> getPatientTherapyHistory(String patientId);

    List<Therapy_Session> getAllPatients();

    List<String> getPatientIdsFromTherapySessions();

//  boolean updateSession(String sessionDate);
}
