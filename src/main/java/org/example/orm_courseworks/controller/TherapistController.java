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
import org.example.orm_courseworks.bo.custom.impl.TherapistBOImpl;
import org.example.orm_courseworks.bo.custom.impl.TherapyProgramBOImpl;
import org.example.orm_courseworks.dto.TherapistDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {

    @FXML
    private TableColumn<TherapistDTO, String> TherapistIdCol;

    @FXML
    private TableColumn<TherapistDTO, String> nameCol;

    @FXML
    private TextField nameTextfield;

    @FXML
    private ComboBox<String> specializationCombobox;

    @FXML
    private TableColumn<TherapistDTO, String> specializationCol;

    @FXML
    private TextField specializationTextfield;

    @FXML
    private AnchorPane therapistAnchorpane;

    @FXML
    private Label therapistIdLabel;

    @FXML
    private TableView<TherapistDTO> therapistTable;

    private final TherapistBOImpl therapistBO = (TherapistBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIST);
    private final TherapyProgramBOImpl therapyProgramBO = (TherapyProgramBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIPROGRAM);

    private String id;

    @FXML
    void addOnAction(ActionEvent event) {
        String id = this.id;
        String name = nameTextfield.getText();
        String specialization = specializationCombobox.getValue();

        if (name.isEmpty() || specialization.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.show();
            return;
        }

        TherapistDTO therapistDTO = new TherapistDTO();
        therapistDTO.setTherapistId(id);
        therapistDTO.setTherapistName(name);
        therapistDTO.setSpecialization(specialization);

        boolean isAdded = therapistBO.save(therapistDTO);

        if (isAdded) {
            nameTextfield.clear();
            specializationCombobox.setValue(null);
            this.id = String.valueOf(therapistBO.getLastPK().orElse("Error"));
            loadTherapistTable();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add therapist");
            alert.show();
            return;
        }
    }

    private void loadTherapistTable() {
        List<TherapistDTO> therapistList = therapistBO.getAll();
        ObservableList<TherapistDTO> therapistDTOObservableList = FXCollections.observableArrayList(therapistList);
        therapistTable.setItems(therapistDTOObservableList);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = therapistIdLabel.getText();
        boolean isDeleted = therapistBO.deleteByPK(id);

        if (isDeleted) {
            nameTextfield.clear();
            specializationCombobox.setValue(null);
            specializationCombobox.setDisable(false);
            therapistIdLabel.setText(id);
            loadTherapistTable();
        } else {
            System.out.println("Failed to delete therapist");
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {
        nameTextfield.clear();
        specializationCombobox.setValue(null);
        specializationTextfield.setDisable(false);
        therapistIdLabel.setText(id);
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = therapistIdLabel.getText();
        String name = nameTextfield.getText();
        specializationCombobox.setDisable(true);
        String specialization = specializationCombobox.getValue();

        if(name.isEmpty() || specialization.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.show();
            return;
        }

        TherapistDTO therapistDTO = new TherapistDTO();
        therapistDTO.setTherapistId(id);
        therapistDTO.setTherapistName(name);
        therapistDTO.setSpecialization(specialization);

        boolean isUpdated = therapistBO.update(therapistDTO);

        if(isUpdated){
            nameTextfield.clear();
            specializationCombobox.setValue(null);
            specializationCombobox.setDisable(false);
            loadTherapistTable();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update therapist");
            alert.show();
            return;
        }
    }

    @FXML
    void therapistOnMouseClicked(MouseEvent event) {
        TherapistDTO therapistDTO = therapistTable.getSelectionModel().getSelectedItem();
        if (therapistDTO != null) {
            therapistIdLabel.setText(therapistDTO.getTherapistId());
            nameTextfield.setText(therapistDTO.getTherapistName());
            specializationCombobox.setValue(therapistDTO.getSpecialization());
            specializationTextfield.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.id = String.valueOf(therapistBO.getLastPK().orElse("Error"));
        therapistIdLabel.setText(id);

        ArrayList<String> programList = therapyProgramBO.getProgramList();
        System.out.println(programList);
        specializationCombobox.getItems().addAll(programList);

        TherapistIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTherapistId()));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTherapistName()));
        specializationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSpecialization()));

        loadTherapistTable();
    }
}
