package org.example.orm_courseworks.bo.custom;

import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserBO extends SuperBO {
    boolean checkUser(String username);
    UserDTO checkPassword(String username);
    Optional<String> getLastK();
    boolean save(UserDTO userDTO);
    boolean update(UserDTO userDTO);
    boolean deleteByPK(String id) throws Exception;
    List<UserDTO> getAll();
    Optional<UserDTO> findByPK(String id);
    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
