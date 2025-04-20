package org.example.orm_courseworks.dao.custom.impl;

import org.example.orm_courseworks.dao.custom.TherapySessionDAO;
import org.example.orm_courseworks.entity.TherapySession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    @Override
    public boolean save(TherapySession therapySession) {
        return false;
    }

    @Override
    public boolean update(TherapySession therapySession) {
        return false;
    }

    @Override
    public boolean deleteByPK(String s) throws Exception {
        return false;
    }

    @Override
    public List<TherapySession> getAll() {
        return List.of();
    }

    @Override
    public Optional<TherapySession> findByPK(String s) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
