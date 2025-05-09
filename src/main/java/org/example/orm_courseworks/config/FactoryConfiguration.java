package org.example.orm_courseworks.config;

import org.example.orm_courseworks.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Patient.class).addAnnotatedClass(Program.class).addAnnotatedClass(Therapist.class).addAnnotatedClass(Therapist_Program.class).addAnnotatedClass(Therapy_Session.class).addAnnotatedClass(Payment.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
