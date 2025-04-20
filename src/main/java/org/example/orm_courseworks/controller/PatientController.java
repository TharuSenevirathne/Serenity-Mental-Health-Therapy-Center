package org.example.orm_courseworks.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.impl.PatientBOImpl;
import org.example.orm_courseworks.dto.PatientDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private TableColumn<PatientDTO, String> birthdateCol;

    @FXML
    private DatePicker birthdateDatepicker;

    @FXML
    private TableColumn<PatientDTO, String> genderCol;

    @FXML
    private ComboBox<String> genderCombobox;

    @FXML
    private TableColumn<PatientDTO, String> nameCol;

    @FXML
    private TextField nameTextfield;

    @FXML
    private AnchorPane patientAnchorpane;

    @FXML
    private TableColumn<PatientDTO, String> patientIdCol;

    @FXML
    private Label patientIdLabel;

    @FXML
    private TableView<PatientDTO> patientTable;

    @FXML
    private TableColumn<PatientDTO, String> phoneNoCol;

    @FXML
    private TextField phoneNoTextfield;

    private final PatientBOImpl patientBO = (PatientBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    private String id;

    @FXML
    void addOnAction(ActionEvent event) {
        String id = this.id;
        String name = nameTextfield.getText();
        String phoneNo = phoneNoTextfield.getText();
        String regex = "^07\\d{8}$";
        String gender = genderCombobox.getValue();
        String birthdate = birthdateDatepicker.getValue().toString();

        if (name.isEmpty() || birthdate.isEmpty() || gender.isEmpty() || phoneNo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all the details");
            alert.show();
            return;
        }

        if (phoneNo.matches(regex)) {
            System.out.println("This Phone number is valid : " + phoneNo);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid phone number");
            alert.show();
            return;
        }

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(id);
        patientDTO.setPatientName(name);
        patientDTO.setPhone(phoneNo);
        patientDTO.setGender(gender);
        patientDTO.setBirthDate(birthdate);

        boolean isSaved = patientBO.save(patientDTO);

        if (isSaved) {
            nameTextfield.clear();
            phoneNoTextfield.clear();
            genderCombobox.setValue(null);
            birthdateDatepicker.setValue(null);
            this.id = String.valueOf(patientBO.getLastPK().orElse("Error"));
            loadPatientTable();
        }else {
            System.out.println("Failed to save patient");
        }
    }

    private void loadPatientTable() {
        List<PatientDTO> patientDTOList = patientBO.getAll();
        for (PatientDTO patientDTO : patientDTOList) {
            System.out.println("Id : "+patientDTO.getPatientId());
            System.out.println("Name : "+patientDTO.getPatientName());
            System.out.println("PhoneNo : "+patientDTO.getPhone());
            System.out.println("Gender : "+patientDTO.getGender());
            System.out.println("BirthDate : "+patientDTO.getBirthDate());
            System.out.println("-----------------------------------------");
        }

        ObservableList<PatientDTO> patientDTOObservableList = FXCollections.observableArrayList();
        patientTable.setItems(patientDTOObservableList);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = patientIdLabel.getText();
        boolean isDeleted = patientBO.deleteByPK(id);

        if (isDeleted) {
            nameTextfield.clear();
            phoneNoTextfield.clear();
            genderCombobox.setValue(null);
            birthdateDatepicker.setValue(null);
            patientIdLabel.setText(id);
            birthdateDatepicker.setDisable(false);
            loadPatientTable();
        }else {
            System.out.println("Failed to delete patient");
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {
        nameTextfield.clear();
        phoneNoTextfield.clear();
        genderCombobox.setValue(null);
        birthdateDatepicker.setValue(LocalDate.parse(LocalDate.now().toString()));
        patientIdLabel.setText(id);
        birthdateDatepicker.setDisable(false);
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = patientIdLabel.getText();
        String name = nameTextfield.getText();
        String phoneNo = phoneNoTextfield.getText();
        String regex = "^07\\d{8}$";
        String gender = genderCombobox.getValue();
        String birthdate = birthdateDatepicker.getValue().toString();

        if (name.isEmpty() || phoneNo.isEmpty() || gender.isEmpty() || birthdate.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter all the details");
            alert.show();
            return;
        }

        if (phoneNo.matches(regex)) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setPatientId(id);
            patientDTO.setPatientName(name);
            patientDTO.setPhone(phoneNo);
            patientDTO.setGender(gender);
            patientDTO.setBirthDate(birthdate);

            boolean isUpdated = patientBO.update(patientDTO);

            if (isUpdated) {
                loadPatientTable();
            }else {
                System.out.println("Failed to update patient");
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid phone number");
            alert.show();
            return;
        }
    }


    @FXML
    void patientOnMouseClicked(MouseEvent event) {
        PatientDTO selectedPatient = patientTable.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            patientIdLabel.setText(selectedPatient.getPatientId());
            nameTextfield.setText(selectedPatient.getPatientName());
            phoneNoTextfield.setText(selectedPatient.getPhone());
            genderCombobox.setValue(selectedPatient.getGender());
            birthdateDatepicker.setDisable(true);

            if (selectedPatient.getBirthDate() != null && !selectedPatient.getBirthDate().isEmpty()) {
                birthdateDatepicker.setValue(LocalDate.parse(String.valueOf(LocalDate.parse(selectedPatient.getBirthDate()))));
            }else {
                birthdateDatepicker.setValue(null);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    birthdateDatepicker.setValue(LocalDate.parse(LocalDate.now().toString()));
    genderCombobox.getItems().addAll("Male", "Female");
    this.id = String.valueOf(patientBO.getLastPK().orElse("Error"));
    patientIdLabel.setText(id);

    patientIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatientId()));
    nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatientName()));
    phoneNoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
    genderCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
    birthdateCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthDate()));

    loadPatientTable();
    }
}
