package org.example.orm_courseworks.bo.custom.impl;

import org.example.orm_courseworks.bo.custom.UserBO;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.impl.UserDAOImpl;
import org.example.orm_courseworks.dto.UserDTO;
import org.example.orm_courseworks.entity.User;
import org.example.orm_courseworks.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UserBOImpl implements UserBO {

    UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean checkUser(String username) {
        return userDAO.cheackUser(username);
    }

    @Override
    public UserDTO checkPassword(String username) {
        User user = userDAO.getSelectUser(username);
        return toUserDTO(user);    }

    @Override
    public Optional<String> getLastK() {
        return userDAO.getLastPK();
    }

    @Override
    public boolean save(UserDTO userDTO) {
        String password = PasswordUtil.hashPassword(userDTO.getPassword());
        userDTO.setPassword(password);
        User userEntity = toUser(userDTO);
        return userDAO.save(userEntity);
    }

    public static User toUser(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getPassword(),
                userDTO.getUserRole()
        );
    }

    @Override
    public boolean update(UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean deleteByPK(String id) throws Exception {
        return false;
    }

    @Override
    public List<UserDTO> getAll() {
        return List.of();
    }

    @Override
    public Optional<UserDTO> findByPK(String id) {
        return Optional.empty();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getUserRole()
        );
    }
}

