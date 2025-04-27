package org.example.orm_courseworks.bo.custom;


import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.ProgramDto;

import java.sql.SQLException;

public interface ProgramBO extends SuperBO {
    ObservableList<ProgramDto> getAllPrograms() throws SQLException, ClassNotFoundException;

    boolean addProgram(ProgramDto programDto) throws SQLException, ClassNotFoundException;

    boolean updateProgram(ProgramDto programDto) throws SQLException, ClassNotFoundException;

    boolean deleteProgram(String programId) throws SQLException, ClassNotFoundException;

    ProgramDto searchProgram(String programId);
}
