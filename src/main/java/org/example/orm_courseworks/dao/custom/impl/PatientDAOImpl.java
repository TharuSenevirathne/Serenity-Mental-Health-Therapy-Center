package org.example.orm_courseworks.dao.custom.impl;

import org.example.orm_courseworks.config.FactoryConfiguration;
import org.example.orm_courseworks.dao.custom.PatientDAO;
import org.example.orm_courseworks.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PatientDAOImpl implements PatientDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean deleteByPK(String id) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSessionFactory()) {
            transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, id);

            if (patient != null) {
                session.remove(patient);
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
    public boolean update(Patient patient) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSessionFactory()) {
            transaction = session.beginTransaction();

            session.merge (patient);

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
    public List<Patient> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSessionFactory()) {
            return session.createQuery("FROM Patient", Patient.class).list();
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
            Long lastPk = session
                    .createQuery("SELECT p.id FROM Patient p ORDER BY p.id DESC", Long.class)
                    .setMaxResults(1)
                    .uniqueResult();

            Long newPk = (lastPk != null) ? lastPk + 1 : 1;
            System.out.println(newPk);
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
    public boolean save(Patient patient) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSessionFactory()) {
            transaction = session.beginTransaction();
            session.persist(patient);
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
    public Optional<Patient> findByPK(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
