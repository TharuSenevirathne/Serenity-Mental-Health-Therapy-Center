package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.UserBO;
import org.example.orm_courseworks.bo.custom.impl.UserBOImpl;
import org.example.orm_courseworks.dao.DAOFactory;
import org.example.orm_courseworks.dao.custom.UserDAO;
import org.example.orm_courseworks.dto.UserDTO;
import org.example.orm_courseworks.util.PasswordUtil;
import org.springframework.security.crypto.bcrypt.BCrypt;

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

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);


    static UserDTO userDTO ;
    public Hyperlink registerLink;

    public static UserDTO getUserDto() {
        return userDTO;
    }

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
            new Alert(Alert.AlertType.ERROR, "Please fill in both username and password.").show();
        } else {
            UserDTO userDto1 = userBO.loginUser(username);
            if (userDto1 == null) {
                new Alert(Alert.AlertType.INFORMATION, "User not found.").show();
            } else {
                if (BCrypt.checkpw(password, userDto1.getPassword())) {
                    System.out.println(userDto1.getRole());
                    userDto1 = userDto1;
                    System.out.println(userDto1.getRole()+"originl");

                    getdashboard();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid password").show();
                }
            }
        }
    }

    private void getdashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) registerLink.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registration Page");
    }

    @FXML
    void signupOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Signup.fxml"));
        MainAnchorpane.getChildren().clear();
        MainAnchorpane.getChildren().add(load);
    }
}