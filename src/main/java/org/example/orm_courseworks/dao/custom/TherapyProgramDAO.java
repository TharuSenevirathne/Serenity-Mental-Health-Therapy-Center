package org.example.orm_courseworks.dao.custom;

import org.example.orm_courseworks.dao.CrudDAO;
import org.example.orm_courseworks.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TherapyProgramDAO extends CrudDAO<TherapyProgram,String> {
    ArrayList<String> getProgramList();
}
