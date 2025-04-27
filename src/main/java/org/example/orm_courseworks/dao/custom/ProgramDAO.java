package org.example.orm_courseworks.dao.custom;


import org.example.orm_courseworks.dao.CrudDAO;
import org.example.orm_courseworks.entity.Program;

public interface ProgramDAO extends CrudDAO<Program> {
    Program get(String programId);

    Program search(String programId);
}
