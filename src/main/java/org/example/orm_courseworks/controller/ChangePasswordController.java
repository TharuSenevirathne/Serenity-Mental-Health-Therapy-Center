package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.UserBO;
import org.example.orm_courseworks.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class ChangePasswordController {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    private AnchorPane MainAnchorpane;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private PasswordField confirmpasswordTextfield;

    @FXML
    private PasswordField newpasswordTextfield;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label passwordLabel1;

    @FXML
    private Label usernameLabel1;

    @FXML
    private TextField usernameTextfield;

    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void confirmOnAction(ActionEvent event) {
        String username = usernameTextfield.getText();
        if (!username.isEmpty()) {
            UserDTO userDTO = userBO.getData(username);
            if (userDTO != null) {
                if (newpasswordTextfield.getText().equals(confirmpasswordTextfield.getText())) {
                    String newPassword = newpasswordTextfield.getText();
                    String encryptedPassword = null;
                    try {
                        encryptedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Error while encrypting password").show();
                        return;
                    }
                    userDTO.setPassword(encryptedPassword);
                    boolean isUpdated = userBO.updateUser(userDTO);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Password Changed").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Password Not Changed").show();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR, "Passwords does'not match").show();
                    newpasswordTextfield.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "User Not Found").show();
                usernameTextfield.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }

        }
    }

}

