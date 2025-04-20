package org.example.orm_courseworks.bo.custom;

import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.TherapyProgramDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapyProgramBO extends SuperBO {
    ArrayList<String> getProgramList();
    boolean save(TherapyProgramDTO therapyProgramDTO);
    Optional<String> getLastPK();
    Optional<TherapyProgramDTO> findByPK(String pk);
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    List<TherapyProgramDTO> getAll();
    boolean deleteByPK(String id);
    boolean update(TherapyProgramDTO therapyProgramDTO);
}
