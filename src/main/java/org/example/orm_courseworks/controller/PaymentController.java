package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PaymentController {

    @FXML
    private TextField AmountTextfield;

    @FXML
    private Label payingamountTextfield;

    @FXML
    private TextField remainingTextfield;

    @FXML
    private TableColumn<String, ?> amountCol;

    @FXML
    private TableColumn<String, ?> dateCol;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private AnchorPane patientAnchorpane;

    @FXML
    private TableColumn<String, ?> patientCol;

    @FXML
    private ComboBox<String> patientCombobox;

    @FXML
    private Label patientidLabel;

    @FXML
    private TableColumn<String, ?> paymentCol;

    @FXML
    private TableView<?> paymentTable;

    @FXML
    private TableColumn<String, ?> programCol;

    @FXML
    private ComboBox<String> programCombobox;

    @FXML
    private ComboBox<String> sessionIdCombobox;

    @FXML
    private TableColumn<String, ?> sessionidCol;

    @FXML
    void InvoiceOnAction(ActionEvent event) {

    }

    @FXML
    void addOnAction(ActionEvent event) {

    }

    @FXML
    void paymentOnMouseCliked(MouseEvent event) {

    }

    @FXML
    void resetOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
