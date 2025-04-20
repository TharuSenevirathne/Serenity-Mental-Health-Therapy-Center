package org.example.orm_courseworks.dao.custom.impl;

import org.example.orm_courseworks.config.FactoryConfiguration;
import org.example.orm_courseworks.dao.custom.TherapyProgramDAO;
import org.example.orm_courseworks.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(TherapyProgram therapyProgram) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSessionFactory()) {
            transaction = session.beginTransaction();
            session.persist(therapyProgram);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(TherapyProgram therapyProgram) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSessionFactory()) {
            transaction = session.beginTransaction();
            session.merge (therapyProgram);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByPK(String id) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSessionFactory()) {
            transaction = session.beginTransaction();

            TherapyProgram therapyProgram = session.get(TherapyProgram.class, id);
            if (therapyProgram != null) {
                session.remove(therapyProgram);
            } else {
                return false;
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TherapyProgram> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSessionFactory()) {
            return session.createQuery("FROM TherapyProgram ", TherapyProgram.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = null;
        try {
            session = factoryConfiguration.getSessionFactory();
            String lastPk = session
                    .createQuery("SELECT t.id FROM TherapyProgram t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastPk != null && lastPk.matches("MT\\d+")) {
                int lastNumber = Integer.parseInt(lastPk.substring(2));
                int newNumber = lastNumber + 1;
                String newPk = String.format("MT%04d", newNumber);
                return Optional.of(newPk);
            } else {
                return Optional.of("MT1001");
            }

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
    public ArrayList<String> getProgramList() {
        ArrayList<String> programNames = new ArrayList<>();

        try (Session session = FactoryConfiguration.getInstance().getSessionFactory()) {
            List<String> names = session.createQuery("SELECT tp.therapyProgramName FROM TherapyProgram tp", String.class).list();
            programNames.addAll(names);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return programNames;
    }

    @Override
    public Optional<TherapyProgram> findByPK(String id) {
        return Optional.empty();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}


