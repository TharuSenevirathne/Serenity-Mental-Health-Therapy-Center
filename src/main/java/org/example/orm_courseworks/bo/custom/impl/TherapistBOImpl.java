package org.example.orm_courseworks.bo.custom.impl;

import org.example.orm_courseworks.bo.custom.TherapistBO;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.impl.TherapistDAOImpl;
import org.example.orm_courseworks.dto.TherapistDTO;
import org.example.orm_courseworks.entity.Therapist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public class TherapistBOImpl implements TherapistBO {

    TherapistDAOImpl therapistDAO = (TherapistDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.THERAPIST);

    @Override
    public boolean save(TherapistDTO therapistDTO) {
        Therapist therapist = toEntity(therapistDTO);
        return therapistDAO.save(therapist);
    }

    @Override
    public Optional<String> getLastPK() {
        return therapistDAO.getLastPK();
    }

    @Override
    public boolean deleteByPK(String id) {
        return therapistDAO.deleteByPK(id);
    }

    @Override
    public List<TherapistDTO> getAll() {
        List<TherapistDTO> users = new ArrayList<>();
        List<Therapist> all = therapistDAO.getAll();
        for (Therapist therapist : all) {
            users.add(new TherapistDTO(
                    therapist.getTherapistId(),
                    therapist.getTherapistName(),
                    therapist.getSpecialization()
            ));
        }
        return users;
    }

    @Override
    public boolean update(TherapistDTO therapistDTO) {
        Therapist therapist = toEntity(therapistDTO);
        return therapistDAO.update(therapist);
    }

    @Override
    public Optional<TherapistDTO> findByPK(String id) {
        return Optional.empty();
    }

    @Override
    public boolean exist(String id) throws ClassNotFoundException ,SQLException{
        return false;
    }

    public static TherapistDTO toDTO(Therapist therapist) {
        if (therapist == null) {
            return null;
        }
        return new TherapistDTO(
                therapist.getTherapistId(),
                therapist.getTherapistName(),
                therapist.getSpecialization()
        );
    }

    public static Therapist toEntity(TherapistDTO therapistDTO) {
        if (therapistDTO == null) {
            return null;
        }
        return new Therapist(
                therapistDTO.getTherapistId(),
                therapistDTO.getTherapistName(),
                therapistDTO.getSpecialization(),
                null
        );
    }
}
