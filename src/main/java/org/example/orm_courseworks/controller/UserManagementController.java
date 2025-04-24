package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {


    @FXML
    private TableColumn<?, ?> passwordCol;

    @FXML
    private TextField passwordTextfield;

    @FXML
    private TableColumn<?, ?> roleCol;

    @FXML
    private ComboBox<?> roleCombobox;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private TableView<?> userTable;

    @FXML
    private TextField useridTextfield;

    @FXML
    private TableColumn<?, ?> usernameCol;

    @FXML
    private TextField usernameTextfield;

    @FXML
    void addOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void resechangepasswordOnAction(ActionEvent event) {

    }

    @FXML
    void resetOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @FXML
    void userOnMouseClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
