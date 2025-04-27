package org.example.orm_courseworks.dao.custom;


import org.example.orm_courseworks.dao.CrudDAO;
import org.example.orm_courseworks.entity.User;

public interface UserDAO extends CrudDAO<User> {
    boolean registerUser(User user);

    User loginUser(String username);

    boolean ifHaveAdmin();

    User search(String id);

}
