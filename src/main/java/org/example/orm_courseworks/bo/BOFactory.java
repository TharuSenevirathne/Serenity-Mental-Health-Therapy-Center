package org.example.orm_courseworks.bo;

import org.example.orm_courseworks.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        PATIENT,PAYMENT,THERAPIST,THERAPIPROGRAM,THERAPYSESSION, QUERY, USER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case PATIENT:
                return new PatientBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case THERAPIST:
                return new TherapistBOImpl();
            case THERAPIPROGRAM:
                return new TherapyProgramBOImpl();
            case THERAPYSESSION:
                return new TherapySessionBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
