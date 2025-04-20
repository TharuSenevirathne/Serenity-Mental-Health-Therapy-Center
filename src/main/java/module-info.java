module ORM.CourseWorks {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires lombok;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.mail;


    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires bcrypt;
    requires java.management;
    requires modelmapper;
    requires java.desktop;

    opens org.example.orm_courseworks.entity to org.hibernate.orm.core;
    opens org.example.orm_courseworks.config to jakarta.persistence;

    opens org.example.orm_courseworks.tdm to javafx.base;
    opens org.example.orm_courseworks.controller to javafx.fxml;

    exports org.example.orm_courseworks;
    exports org.example.orm_courseworks.controller;
}
