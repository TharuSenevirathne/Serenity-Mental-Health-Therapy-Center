package org.example.orm_courseworks.bo.custom;

import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.ProgramDTO;

import java.sql.SQLException;

public interface ProgramBO extends SuperBO {
    ObservableList<ProgramDTO> getAllPrograms() throws SQLException, ClassNotFoundException;
    boolean addProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;
    boolean updateProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;
    boolean deleteProgram(String programId) throws SQLException, ClassNotFoundException;
}
