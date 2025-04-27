package org.example.orm_courseworks.dao.custom;


import org.example.orm_courseworks.dao.CrudDAO;
import org.example.orm_courseworks.dto.TherapistDto;
import org.example.orm_courseworks.entity.Therapist;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface TherapistDAO extends CrudDAO<Therapist> {
    Serializable savetherapist(Therapist therapist, Session session);

    List<String> getAvailableTherapists();

    void updateStatus(TherapistDto therapistDTO);

    Serializable updatetherapist(Therapist therapist, Session session);
}
