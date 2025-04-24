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
        Session session = factoryConfiguration.getSessionFactory();

        try {
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, id);

            if (patient != null) {
                session.remove(patient);
            } else {
                return false;
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Unable to delete Patient!");
            e.printStackTrace();
            return false;
        }finally{
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public boolean update(Patient patient) {
        Session session = factoryConfiguration.getSessionFactory();

        try {
            Transaction transaction = session.beginTransaction();
            session.merge (patient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Unable to update Patient!");
            e.printStackTrace();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Patient> getAll() {
        Session session = factoryConfiguration.getSessionFactory();

        try {
            return session.createQuery("FROM Patient", Patient.class).list();
        } catch (Exception e) {
            System.out.println("Unable to getAll Patients!");
            e.printStackTrace();
            return Collections.emptyList();
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSessionFactory();

        try {
            Long lastPk = session
                    .createQuery("SELECT p.id FROM Patient p ORDER BY p.id DESC", Long.class)
                    .setMaxResults(1)
                    .uniqueResult();

            Long newPk = (lastPk != null) ? lastPk + 1 : 1;
            System.out.println(newPk);
            return Optional.of(String.valueOf(newPk));
        } catch (Exception e) {
            System.out.println("Unable to getLastPK!");
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
        Session session = factoryConfiguration.getSessionFactory();

        try {
            Transaction transaction = session.beginTransaction();
            session.persist(patient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Unable to save Patient!");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Patient> findByPK(String id) {
        return Optional.empty();
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
