package org.example.orm_courseworks.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.UserBO;
import org.example.orm_courseworks.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

public class RegistrationController {
    public TextField txtUsername;
    public TextField txtEmail;
    public PasswordField txtPassword;
    public Button btnRegister;
    public Button backbit;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registraton form");
        stage.show();
    }

    public void registerOnAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();

        } else {
            String encryptedPassword = null;
            try {
                encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error while encrypting password").show();
                return;
            }

            UserDto userDto = new UserDto(username, email, encryptedPassword, "admin");

            boolean isRegistered = userBO.registerUser(userDto);
            if (isRegistered) {
                new Alert(Alert.AlertType.INFORMATION, "Registration successful.").show();
                backOnAction(actionEvent);
            } else {
                new Alert(Alert.AlertType.ERROR, "Registration failed. Please try again.").show();

            }
        }
    }
}