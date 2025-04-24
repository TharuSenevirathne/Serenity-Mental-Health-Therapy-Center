package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditProfileController {

    @FXML
    private PasswordField ConfirmPasswordTextfield;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private AnchorPane editProfileAnchorpane;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label passwordLabel1;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Label usernameLabel1;

    @FXML
    private TextField usernameTextfield;

    @FXML
    void cancelOnAction(ActionEvent event) {

    }

    @FXML
    void confirmOnAction(ActionEvent event) {

    }
}
