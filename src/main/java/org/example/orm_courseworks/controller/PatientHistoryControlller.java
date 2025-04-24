package org.example.orm_courseworks.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.PatientBO;
import org.example.orm_courseworks.bo.custom.TherapySessionBO;
import org.example.orm_courseworks.dto.PatientDTO;
import org.example.orm_courseworks.dto.ProgramHistoryDTO;

import java.sql.SQLException;
import java.util.List;

public class PatientHistoryControlller {

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox<String> cmbPatientId;

    @FXML
    private TableColumn<ProgramHistoryDTO, String> colFee;

    @FXML
    private TableColumn<ProgramHistoryDTO, String> colPaymentDate;

    @FXML
    private TableColumn<ProgramHistoryDTO, String> colProgramId;

    @FXML
    private TableColumn<ProgramHistoryDTO, String> colProgramName;

    @FXML
    private TableColumn<ProgramHistoryDTO, String> colRemainAmount;

    @FXML
    private TableColumn<ProgramHistoryDTO, String> colTherapistName;

    @FXML
    private Label lblContactNumber;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblRegistrationDate;

    @FXML
    private TableView<ProgramHistoryDTO> tblTherapyPrograms;

    PatientBO patientBO = (PatientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    TherapySessionBO sessionBO = (TherapySessionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPYSESSION);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCmbPatientId();
        setCellValue();
    }

    private void setCmbPatientId() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();

        List<PatientDTO> patientIds = patientBO.getAllPatients();
        for (PatientDTO patientDto : patientIds) {
            observableList.add(patientDto.getPatientId());
        }
        cmbPatientId.setItems(observableList);
    }

    public void setCellValue() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colTherapistName.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colRemainAmount.setCellValueFactory(new PropertyValueFactory<>("remainingAmount"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
    }

    @FXML
    void cmbPatientId(ActionEvent event) throws SQLException, ClassNotFoundException {

    }

    @FXML
    void searchPatient(ActionEvent event) {
        String patientId = cmbPatientId.getSelectionModel().getSelectedItem();
        if (patientId == null) return;

        // Load patient basic information
        PatientDTO patient = patientBO.searchPatient(patientId);
        if (patient != null) {
            lblPatientName.setText(patient.getPatientName());
            lblContactNumber.setText(patient.getPhone());
            lblRegistrationDate.setText(patient.getRegDate());

            // Load therapy program history for this patient
            loadTherapyProgramHistory(patientId);
        }
    }

    private void loadTherapyProgramHistory(String patientId) {
        try {
            List<ProgramHistoryDTO> historyList = sessionBO.getPatientTherapyHistory(patientId);
            ObservableList<ProgramHistoryDTO> observableList = FXCollections.observableArrayList(historyList);
            tblTherapyPrograms.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load therapy history: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }
}
