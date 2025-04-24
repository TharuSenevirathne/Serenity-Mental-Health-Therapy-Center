package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.PaymentBO;
import org.example.orm_courseworks.bo.custom.impl.PatientBOImpl;
import org.example.orm_courseworks.bo.custom.impl.PaymentBOImpl;
import org.example.orm_courseworks.bo.custom.impl.QueryBOImpl;
import org.example.orm_courseworks.dao.custom.impl.PaymentDAOImpl;
import org.example.orm_courseworks.dto.PaymentDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private TextField AmountTextfield;

    @FXML
    private Label payingamountTextfield;

    @FXML
    private TextField remainingTextfield;

    @FXML
    private TableColumn<PaymentDTO, String> amountCol;

    @FXML
    private TableColumn<PaymentDTO, String> dateCol;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private AnchorPane patientAnchorpane;

    @FXML
    private TableColumn<PaymentDTO, String> patientCol;

    @FXML
    private ComboBox<String> patientCombobox;

    @FXML
    private Label patientidLabel;

    @FXML
    private TableColumn<PaymentDTO, String> paymentCol;

    @FXML
    private TableView<PaymentDTO> paymentTable;

    @FXML
    private TableColumn<PaymentDTO, String> programCol;

    @FXML
    private ComboBox<PaymentDTO> programCombobox;

    @FXML
    private ComboBox<String> sessionIdCombobox;

    @FXML
    private TableColumn<PaymentDTO, String> sessionidCol;

    private final PaymentBOImpl paymentBO = (PaymentBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    private final PatientBOImpl patientBO = (PatientBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    private final QueryBOImpl quoryBO = (QueryBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.QUERY);

    private ArrayList<String> patientDetails;

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
        lblPaymentId.setText(paymentBO.getLastPK().orElse("0"));
        selectPatient.setValue(null);
        lblAmount.setText("");
        lblSession.setText("");
        dateDatePicker.setValue(LocalDate.parse(LocalDate.now().toString()));
    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblPaymentId.setText(paymentBO.getLastPK().orElse("0"));

        List<String> patientList = patientBO.patientList();
        selectPatient.getItems().addAll(patientList);
        lblDate.setText(LocalDate.now().toString());

        colPaymentId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));


        loadPatientTable();
    }

    private void loadPatientTable() {
        List<PaymentDTO> paymentList = paymentBO.getAll();
        ObservableList<PaymentDTO> paymentTMS = FXCollections.observableArrayList(paymentList);
        tblPayments.setItems(paymentTMS);
    }
}
