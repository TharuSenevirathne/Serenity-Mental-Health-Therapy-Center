package org.example.orm_courseworks.dao;

import org.example.orm_courseworks.entity.SuperEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity, Id > extends SuperDAO {
    boolean save(T t);
    boolean update(T t);
    boolean deleteByPK(Id id) throws Exception;
    List<T> getAll();
    Optional<T> findByPK(Id id)throws Exception;
    Optional<String> getLastPK();
    boolean exist(String id) throws SQLException,ClassNotFoundException;
}