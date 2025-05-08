package org.example.orm_courseworks.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.UserBO;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.UserDAO;
import org.example.orm_courseworks.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public Button btnLogin;
    public Hyperlink registerLink;
    public Button btnTogglePassword;
    public TextField txtPasswordVisible;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    static UserDto userDto ;
    public void initialize() {
        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            txtPasswordVisible.setText(newValue);
        });

        txtPasswordVisible.textProperty().addListener((observable, oldValue, newValue) -> {
            txtPassword.setText(newValue);
        });
    }
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.isVisible() ? txtPassword.getText() : txtPasswordVisible.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in both username and password.").show();
        } else {
            UserDto userDto1 = userBO.loginUser(username);
            if (userDto1 == null) {
                new Alert(Alert.AlertType.INFORMATION, "User not found.").show();
            } else {
                if (BCrypt.checkpw(password, userDto1.getPassword())) {
                        System.out.println(userDto1.getRole());
                        userDto = userDto1;
                        System.out.println(userDto.getRole()+"originl");

                    getdashboard();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid password").show();
                }
            }
        }
    }

    void getdashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) registerLink.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registration Page");
    }

    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
        if (userDAO.ifHaveAdmin()) {
            new Alert(Alert.AlertType.ERROR, "Already have an admin.").show();
        }else {
                AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));

                Scene scene = new Scene(rootNode);

                Stage stage = (Stage) registerLink.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Registration Page");
        }
    }
    static UserDto getUserDto() {
        return userDto;
    }

    public void togglePasswordVisibility(ActionEvent actionEvent) {
        if (txtPassword.isVisible()) {
            txtPasswordVisible.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtPasswordVisible.setVisible(true);
            btnTogglePassword.setText("👀");
        } else {
            txtPassword.setText(txtPasswordVisible.getText());
            txtPasswordVisible.setVisible(false);
            txtPassword.setVisible(true);
            btnTogglePassword.setText("🙈");
        }
    }
}
