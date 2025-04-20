package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminDashboardController {

        @FXML
        private AnchorPane MainAnchorpane;

        @FXML
        private AnchorPane PatientAnchorpane;

        @FXML
        private AnchorPane ProgramAnchorpane;

        @FXML
        private AnchorPane SessionAnchorpane;

        @FXML
        private AnchorPane TherapistAnchorpane;

        @FXML
        private AnchorPane sideAnchorpane;

        @FXML
        void editProfileOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/EditProfile.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void homeOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Admin-Dashboard.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void logOutOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void patientOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Patient.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void paymentOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Payment.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void reportOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Report.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void therapistOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Therapist.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void therapyProgramOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Therapy-Program.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void therapySessionOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Therapy-Session.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

    }
