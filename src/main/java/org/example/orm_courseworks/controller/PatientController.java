package org.example.orm_courseworks.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.impl.PatientBOImpl;
import org.example.orm_courseworks.dto.PatientDTO;
import org.example.orm_courseworks.dto.UserDTO;

import java.net.URL;
import java.sql.SQLException;
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
    static UserDTO userDTO;
    public Button btnPatientHistory;

    public void initialize() throws SQLException, ClassNotFoundException {
        userDTO = LoginController.getUserDto();
        getallPatient();
        checkrole(userDTO);
        clear();
    }

    private void checkrole(UserDTO userDTO) {
         /*System.out.println(userDto.getRole());

        if (userDto.getRole().equals("admin")){
            btnAddPatient.setDisable(true);
            btnDeletePatient.setDisable(true);
            btnUpdatePatient.setDisable(true);
        }*/
    }

    private void getallPatient() throws SQLException, ClassNotFoundException {
        ObservableList<PatientDTO> patientDtos = patientBO.getAllPatients();
        patientTable.setItems(patientDtos);
    }


    public void clear() {
        nameTextfield.clear();
        phoneNoTextfield.clear();
    }

    @FXML
    void addOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = this.id;
        String name = nameTextfield.getText();
        String contact = phoneNoTextfield.getText();
        String regex = "^07\\d{8}$";
        String gender = genderCombobox.getValue();
        String regDate = birthdateDatepicker.getValue().toString();

        if(name.isEmpty() || contact.isEmpty() || gender.isEmpty() || regDate.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.show();

            return;
        }

        if (contact.matches(regex)) {
            System.out.println("Valid contact number: " + contact);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid contact number");
            alert.show();
            return;
        }


        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(id);
        patientDTO.setPatientName(name);
        patientDTO.setPhone(contact);
        patientDTO.setGender(gender);
        patientDTO.setRegDate(regDate);

        boolean isSaved = patientBO.save(patientDTO);

        if (isSaved) {
            nameTextfield.clear();
            phoneNoTextfield.clear();
            genderCombobox.setValue(null);
            birthdateDatepicker.setValue(null);
            patientIdLabel.setText(patientBO.getLastPK().orElse("Error"));

            loadPatientTable();
        } else {
            System.out.println("Failed to save patient");
        }
    }

    private void loadPatientTable() {
        List<PatientDTO> patientDTOList = patientBO.getAll();

        ObservableList<PatientDTO> patientDTOObservableList = FXCollections.observableArrayList(patientDTOList);
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
            patientDTO.setRegDate(birthdate);

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

            if (selectedPatient.getRegDate() != null && !selectedPatient.getRegDate().isEmpty()) {
                birthdateDatepicker.setValue(LocalDate.parse(String.valueOf(LocalDate.parse(selectedPatient.getRegDate()))));
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
    birthdateCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegDate()));

    loadPatientTable();
    }
}
