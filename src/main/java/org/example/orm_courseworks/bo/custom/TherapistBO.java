package org.example.orm_courseworks.bo.custom;

import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.TherapistDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TherapistBO extends SuperBO {
    boolean save(TherapistDTO therapistDTO);
    Optional<String> getLastPK();
    boolean deleteByPK(String id);
    List<TherapistDTO> getAll();
    boolean update(TherapistDTO therapistDTO);
    Optional<TherapistDTO> findByPK(String id);
    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
