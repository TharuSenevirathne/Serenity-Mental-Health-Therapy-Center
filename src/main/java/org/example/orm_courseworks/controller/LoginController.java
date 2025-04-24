package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.impl.UserBOImpl;
import org.example.orm_courseworks.dto.UserDTO;
import org.example.orm_courseworks.util.PasswordUtil;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane MainAnchorpane;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Label roleLabel;

    @FXML
    private Button signupbutton;

    @FXML
    private Label usernameLabel1;

    @FXML
    private TextField usernameTextfield;

    private final UserBOImpl userBO = (UserBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        String username = usernameTextfield.getText();
        String password = passwordTextfield.getText();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or Password is empty!!");
            return;
        }

        boolean result = userBO.checkUser(username);

        if (result) {
            UserDTO userDTO = userBO.checkPassword(username);
            String role = userDTO.getUserRole();
            String hashedDTO = userDTO.getPassword();

            System.out.println("In Controller " + hashedDTO);
            System.out.println(role);


            boolean isPasswordValid = PasswordUtil.verifyPassword(password, hashedDTO);

            if (!isPasswordValid) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Password!");
                alert.show();
            }else {
                if (role.equals("Admin")) {
                    MainAnchorpane.getChildren().clear();
                    MainAnchorpane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml")));
                } else if (role.equals("Receptionist")) {
                    MainAnchorpane.getChildren().clear();
                    MainAnchorpane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Recetionist-Dashboard.fxml")));
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Username or Password!");
            alert.showAndWait();
        }
    }

    @FXML
    void signupOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Signup.fxml"));
        MainAnchorpane.getChildren().clear();
        MainAnchorpane.getChildren().add(load);
    }
}