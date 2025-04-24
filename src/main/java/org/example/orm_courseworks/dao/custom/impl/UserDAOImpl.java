package org.example.orm_courseworks.dao.custom.impl;

import org.example.orm_courseworks.config.FactoryConfiguration;
import org.example.orm_courseworks.dao.custom.UserDAO;
import org.example.orm_courseworks.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(User userEntity) {
        Session session = factoryConfiguration.getSessionFactory();

        try {
            Transaction transaction = session.beginTransaction();
            session.persist(userEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean cheackUser(String userName) {
        Session session = factoryConfiguration.getSessionFactory();
        try {
            String username = session.createQuery("SELECT u.username FROM User u WHERE u.username = :username", String.class)
                    .setParameter("username", userName)
                    .uniqueResult();

            return username != null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User getSelectUser(String username) {
        Session session = factoryConfiguration.getSessionFactory();
        try {
            User user = session.createQuery("FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();

            System.out.println(user.getPassword());
            System.out.println(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSessionFactory();
        try {
            Long lastPk = session
                    .createQuery("SELECT u.id FROM User u ORDER BY u.id DESC", Long.class)
                    .setMaxResults(1)
                    .uniqueResult();

            Long newPk = (lastPk != null) ? lastPk + 1 : 1;
            return Optional.of(String.valueOf(newPk));

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean deleteByPK(String id) throws Exception {
        return false;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public Optional<User> findByPK(String id) {
        return Optional.empty();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}