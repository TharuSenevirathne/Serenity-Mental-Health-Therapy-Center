module ORM.CourseWorks {
//    requires javafx.fxml;
//    requires javafx.graphics;
//    requires javafx.controls;
//    requires lombok;
//    requires java.sql;
//    requires net.sf.jasperreports.core;
//    requires java.mail;
//    requires spring.security.crypto;

    requires javafx.fxml;
    requires lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires spring.security.crypto;
    requires net.sf.jasperreports.core;

    requires bcrypt;
    requires java.management;
    requires modelmapper;
    requires java.desktop;
    requires javafx.controls;

//    opens org.example.orm_courseworks.entity to org.hibernate.orm.core;
//    opens org.example.orm_courseworks.config to jakarta.persistence;
//
//    opens org.example.orm_courseworks to javafx.fxml;
//    exports org.example.orm_courseworks;
//    exports org.example.orm_courseworks.controllers;
//
//    opens org.example.orm_courseworks.controller to javafx.fxml;
//    exports org.example.orm_courseworks.config;
//
//    opens config to javafx.fxml;
//    exports org.example.orm_courseworks.dao.custom;
//
//    opens org.example.orm_courseworks.dao.custom to javafx.fxml;
//    exports org.example.orm_courseworks.bo.custom;
//
//    opens org.example.orm_courseworks.bo.custom to javafx.fxml;
//    exports org.example.orm_courseworks.entity;
//
//    opens entity to org.hibernate.orm.core;
//    exports org.example.orm_courseworks.dto;
//    opens dto to org.hibernate.orm.core;

    opens org.example.orm_courseworks to javafx.fxml;
    exports org.example.orm_courseworks;
    exports org.example.orm_courseworks.controllers;

    opens org.example.orm_courseworks.controllers to javafx.fxml;
    exports org.example.orm_courseworks.config;

    opens org.example.orm_courseworks.config to javafx.fxml;
    exports org.example.orm_courseworks.dao.custom;

    opens org.example.orm_courseworks.dao.custom to javafx.fxml;
    exports org.example.orm_courseworks.bo.custom;

    opens org.example.orm_courseworks.bo.custom to javafx.fxml;
    exports org.example.orm_courseworks.entity;

    opens org.example.orm_courseworks.entity to org.hibernate.orm.core;
    exports org.example.orm_courseworks.dto;

    opens org.example.orm_courseworks.dto to org.hibernate.orm.core;


}


