package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TherapySessionController {

    @FXML
    private ComboBox<String> StatusCombobox;

    @FXML
    private ComboBox<String> TimeCombobox;

    @FXML
    private TableColumn<String, ?> dateCol;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private TableColumn<String, ?> patientCol;

    @FXML
    private ComboBox<String> patientComboboc;

    @FXML
    private TableColumn<String, ?> programCol;

    @FXML
    private TableColumn<String, ?> sessionIdCol;

    @FXML
    private Label sessionIdLabel;

    @FXML
    private TableColumn<String, ?> statusCol;

    @FXML
    private TableColumn<String, ?> therapistCol;

    @FXML
    private ComboBox<String> therapistCombobox;

    @FXML
    private ComboBox<String> therapyProgramCombobox;

    @FXML
    private AnchorPane therapySessionAnchorpane;

    @FXML
    private TableView<?> therapySessionTable;

    @FXML
    private TableColumn<String, ?> timeCol;

    @FXML
    void addOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void resetOnAction(ActionEvent event) {

    }

    @FXML
    void therapySessionOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }
}
