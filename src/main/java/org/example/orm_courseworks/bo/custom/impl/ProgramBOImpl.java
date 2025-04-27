package org.example.orm_courseworks.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.custom.ProgramBO;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.ProgramDAO;
import org.example.orm_courseworks.dto.ProgramDto;
import org.example.orm_courseworks.entity.Program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROGRAM);
    @Override
    public ObservableList<ProgramDto> getAllPrograms() throws SQLException, ClassNotFoundException {
        List<ProgramDto> programDtos = new ArrayList<>();
        List<Program> programs = programDAO.getAll();
        for (Program program : programs) {
            programDtos.add(new ProgramDto(program.getProgramId(),program.getName(),program.getDuration(),program.getFee()));
        }
        return FXCollections.observableArrayList(programDtos);
    }

    @Override
    public boolean addProgram(ProgramDto programDto) throws SQLException, ClassNotFoundException {
        return programDAO.save(new Program(programDto.getProgramId(),programDto.getName(),programDto.getDuration(),programDto.getFee()));
    }

    @Override
    public boolean updateProgram(ProgramDto programDto) throws SQLException, ClassNotFoundException {
        return programDAO.update(new Program(programDto.getProgramId(),programDto.getName(),programDto.getDuration(),programDto.getFee()));
    }

    @Override
    public boolean deleteProgram(String programId) throws SQLException, ClassNotFoundException {
        return  programDAO.delete(programId);
    }

    @Override
    public ProgramDto searchProgram(String programId) {
        Program program = programDAO.search(programId);
        if (program == null){
            return null;
        }else {
            return new ProgramDto(program.getProgramId(),program.getName(),program.getDuration(),program.getFee());
        }
    }

}
