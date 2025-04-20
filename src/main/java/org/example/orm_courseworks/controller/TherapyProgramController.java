package org.example.orm_courseworks.controller;

import javafx.beans.property.SimpleObjectProperty;
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
import org.example.orm_courseworks.dto.TherapyProgramDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapyProgramController implements Initializable {

    @FXML
    private TableView<TherapyProgramDTO> TherapyProgramTable;

    @FXML
    private TableColumn<TherapyProgramDTO, String> durationCol;

    @FXML
    private ComboBox<String> selectTimeCombobox;

    @FXML
    private TextField durationTextfield;

    @FXML
    private TableColumn<TherapyProgramDTO,String> feeCol;

    @FXML
    private TextField feeTextfield;

    @FXML
    private TableColumn<TherapyProgramDTO, String> nameCol;

    @FXML
    private TextField nameLabel;

    @FXML
    private Label programIdLabel;

    @FXML
    private TableColumn<TherapyProgramDTO,String> programidCol;

    @FXML
    private AnchorPane therapyProgramAnchorpane;

    private final TherapistBOImpl therapistBO = (TherapistBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIST);
    private final TherapyProgramBOImpl therapyProgramBO = (TherapyProgramBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIPROGRAM);

    private String id;

    @FXML
    void addOnAction(ActionEvent event) {
        String id = this.id;
        String name = nameLabel.getText();
        String duration = durationTextfield.getText() + " " + selectTimeCombobox.getValue();
        String fee = feeTextfield.getText();

        if(name.isEmpty() || duration.isEmpty() || fee.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.show();
            return;
        }

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO();
        therapyProgramDTO.setTherapyProgramId(id);
        therapyProgramDTO.setTherapyProgramName(name);
        therapyProgramDTO.setTherapyProgramDuration(duration);
        therapyProgramDTO.setTherapyProgramCost(Double.parseDouble(fee));


        boolean isAdded = therapyProgramBO.save(therapyProgramDTO);

        if(isAdded){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Therapy Program added successfully");
            alert.show();

            this.id = therapyProgramBO.getLastPK().orElse("0");
            programIdLabel.setText(this.id);
            nameLabel.clear();
            durationTextfield.clear();
            feeTextfield.clear();
            selectTimeCombobox.setValue(null);
            loadTherapyProgramTable();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Therapy Program not added");
            alert.show();
            return;
        }
    }

    private void loadTherapyProgramTable() {
        List<TherapyProgramDTO> therapyProgramList = therapyProgramBO.getAll();
        ObservableList<TherapyProgramDTO> therapyProgramDTOObservableList = FXCollections.observableArrayList(therapyProgramList);
        TherapyProgramTable.setItems(therapyProgramDTOObservableList);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = programIdLabel.getText();
        boolean isDeleted = therapyProgramBO.deleteByPK(id);

        if (isDeleted) {
            nameLabel.clear();
            durationTextfield.clear();
            feeTextfield.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Therapy Program deleted successfully");
            alert.show();
            selectTimeCombobox.setValue(null);
            programIdLabel.setText(this.id);
            loadTherapyProgramTable();
        } else {
            System.out.println("Failed to delete");
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {
        nameLabel.clear();
        durationTextfield.clear();
        feeTextfield.clear();
        selectTimeCombobox.setValue(null);
        programidCol.setText(this.id);
    }

    @FXML
    void therapyProgramOnMouseClicked(MouseEvent event) {
        TherapyProgramDTO selectedPatient = TherapyProgramTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            programIdLabel.setText(selectedPatient.getTherapyProgramId());
            nameLabel.setText(selectedPatient.getTherapyProgramName());
            String duration = selectedPatient.getTherapyProgramDuration();
            String[] parts = duration.split(" ");
            durationTextfield.setText(parts[0]);
            selectTimeCombobox.setValue(parts[1]);
            feeTextfield.setText(String.valueOf(selectedPatient.getTherapyProgramCost()));
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = programIdLabel.getText();
        String name = nameLabel.getText();
        String duration = durationTextfield.getText() + " " + selectTimeCombobox.getValue();
        String fee = feeTextfield.getText();

        if(name.isEmpty() || duration.isEmpty() || fee.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.show();
            return;
        }

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO();
        therapyProgramDTO.setTherapyProgramId(id);
        therapyProgramDTO.setTherapyProgramName(name);
        therapyProgramDTO.setTherapyProgramDuration(duration);
        therapyProgramDTO.setTherapyProgramCost(Double.parseDouble(fee));

        boolean isUpdated = therapyProgramBO.update(therapyProgramDTO);

        if(isUpdated){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Therapy Program updated successfully");
            alert.show();

            this.id = therapyProgramBO.getLastPK().orElse("0");
            programIdLabel.setText(this.id);
            nameLabel.clear();
            durationTextfield.clear();
            feeTextfield.clear();
            selectTimeCombobox.setValue(null);
            loadTherapyProgramTable();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Therapy Program not updated");
            alert.show();
            return;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectTimeCombobox.getItems().addAll("Weeks", "Months", "Years");
        this.id = therapyProgramBO.getLastPK().orElse("0");
        programIdLabel.setText(this.id);

        programidCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTherapyProgramId()));
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTherapyProgramName()));
        durationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTherapyProgramDuration()));
        feeCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTherapyProgramCost()).asString());
        loadTherapyProgramTable();
    }
}
