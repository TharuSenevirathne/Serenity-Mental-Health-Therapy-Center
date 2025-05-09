package org.example.orm_courseworks.dao;


import org.example.orm_courseworks.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        PATIENT, PROGRAM, USER , SESSION, PAYMENT, THERAPIST

    }
    public SuperDAO getDAO(DAOType type) {
        switch(type) {
            case USER:
                return new UserDAOImpl();
            case PATIENT:
                return new PatientDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case THERAPIST:
                return new TherapistDAOImpl();
            case SESSION:
                return new SessionDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            default:
                return null;
        }
    }
}
