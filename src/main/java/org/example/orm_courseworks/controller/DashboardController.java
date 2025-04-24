package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.example.orm_courseworks.dto.UserDTO;

import java.io.IOException;

public class DashboardController {
    public Label userNameLabel;
    public Label therapistCountLabel;
    public Label patientCountLabel;
    public Label programCountLabel;
    public Label sessionCountLabel;

    static UserDTO userDTO;
    public Button btnuser;
    public Button btntherapist;
    public Button btntherapyProgram;
    public Button btnpatient;
    public Button btnsession;
    public Button btnpayment;
    public Button btnreport;
    public Button btnlogout;
    public HBox role;


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

    public void initialize() throws IOException {
        userDTO = LoginController.getUserDto();
        checkrole(userDTO);
        setLabel();
    }

    private void checkrole(UserDTO userDTO) {
        System.out.println(userDTO.getRole()+"checkrole");
        if (userDTO.getRole().equals("admin")){
            btnuser.setDisable(false);
            btntherapyProgram.setDisable(false);
            btntherapist.setDisable(false);
        }else{
            btnuser.setVisible(false);
            btntherapyProgram.setVisible(false);
            btntherapist.setDisable(false);
            btntherapist.setVisible(false);
        }
    }

    public void setLabel(){
        if (userDTO != null) {
            if (userDTO.getRole().equals("admin")) {
                userNameLabel.setText("Admin User");
            } else if (userDTO.getRole().equals("receptionist")) {
                userNameLabel.setText("Receptionist User");
            } else {
                userNameLabel.setText("Unknown Role");
            }
        }
    }

        @FXML
        void editProfileOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/EditProfile.fxml"));
            sideAnchorpane.getChildren().clear();
            sideAnchorpane.getChildren().add(load);
        }

        @FXML
        void homeOnAction(ActionEvent event) throws IOException {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
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


    @FXML
    void userOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/UserManagement.fxml"));
        sideAnchorpane.getChildren().clear();
        sideAnchorpane.getChildren().add(load);
    }
}
