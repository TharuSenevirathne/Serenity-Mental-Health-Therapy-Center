package org.example.orm_courseworks.bo.custom.impl;

import org.example.orm_courseworks.bo.custom.TherapyProgramBO;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.impl.TherapyProgramDAOImpl;
import org.example.orm_courseworks.dto.TherapyProgramDTO;
import org.example.orm_courseworks.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TherapyProgramBOImpl implements TherapyProgramBO {

    private final TherapyProgramDAOImpl therapyProgramDAO = (TherapyProgramDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.THERAPIPROGRAM);
    
    @Override
    public ArrayList<String> getProgramList() {
        return therapyProgramDAO.getProgramList();
    }

    @Override
    public boolean save(TherapyProgramDTO therapyProgramDTO) {
        TherapyProgram therapyProgramEntity = convertToEntity(therapyProgramDTO);
        return therapyProgramDAO.save(therapyProgramEntity);
    }

    private TherapyProgram convertToEntity(TherapyProgramDTO therapyProgramDTO) {
        if (therapyProgramDTO == null) {
            return null;
        }
        return new TherapyProgram(
                therapyProgramDTO.getTherapyProgramId(),
                therapyProgramDTO.getTherapyProgramName(),
                therapyProgramDTO.getTherapyProgramDuration(),
                therapyProgramDTO.getTherapyProgramCost(),
                null
        );
    }

    @Override
    public Optional<String> getLastPK() {
        return therapyProgramDAO.getLastPK();
    }

    @Override
    public Optional<TherapyProgramDTO> findByPK(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<TherapyProgramDTO> getAll() {
        List<TherapyProgramDTO> users = new ArrayList<>();
        List<TherapyProgram> all = therapyProgramDAO.getAll();
        for (TherapyProgram therapyProgram : all) {
            users.add(new TherapyProgramDTO(
                    therapyProgram.getTherapyProgramId(),
                    therapyProgram.getTherapyProgramName(),
                    therapyProgram.getTherapyProgramDuration(),
                    therapyProgram.getTherapyProgramCost()
            ));
        }
        return users;
    }

    @Override
    public boolean deleteByPK(String id) {
        return therapyProgramDAO.deleteByPK(id);
    }

    @Override
    public boolean update(TherapyProgramDTO therapyProgramDTO) {
        TherapyProgram therapyProgram = toEntity(therapyProgramDTO);
        return therapyProgramDAO.update(therapyProgram);
    }

    public static TherapyProgramDTO convertToDTO(TherapyProgram entity) {
        if (entity == null) {
            return null;
        }
        return new TherapyProgramDTO(
                entity.getTherapyProgramId(),
                entity.getTherapyProgramName(),
                entity.getTherapyProgramDuration(),
                entity.getTherapyProgramCost()
        );
    }

    public static TherapyProgram toEntity(TherapyProgramDTO dto) {
        if (dto == null) {
            return null;
        }
        return new TherapyProgram(
                dto.getTherapyProgramId(),
                dto.getTherapyProgramName(),
                dto.getTherapyProgramDuration(),
                dto.getTherapyProgramCost(),
                 null
        );
    }

    public static TherapyProgramDTO toDTO(TherapyProgram entity) {
        if (entity == null) {
            return null;
        }
        return new TherapyProgramDTO(
                entity.getTherapyProgramId(),
                entity.getTherapyProgramName(),
                entity.getTherapyProgramDuration(),
                entity.getTherapyProgramCost()
        );
    }

}
