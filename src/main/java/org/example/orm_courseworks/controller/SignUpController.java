package org.example.orm_courseworks.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.orm_courseworks.bo.BOFactory;
import org.example.orm_courseworks.bo.custom.impl.UserBOImpl;
import org.example.orm_courseworks.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private AnchorPane MainAnchorpane;

    @FXML
    private Button cancelBUTTON;

    @FXML
    private PasswordField confirmPasswordTextfield;

    @FXML
    private Button loginBUTTON;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label passwordLabel1;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Label roleLabel;

    @FXML
    private Button signUPBUTTON;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameTextfield;

    private final UserBOImpl userBO = (UserBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void cancelONACTION(ActionEvent event) {
        Stage stage = (Stage) cancelBUTTON.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginBUTTONACTION(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        MainAnchorpane.getChildren().clear();
        MainAnchorpane.getChildren().add(load);
    }

    @FXML
    void signUpONACTION(ActionEvent event) throws IOException {
        String username = usernameTextfield.getText();
        String password = passwordTextfield.getText();
        String confirmPassword = confirmPasswordTextfield.getText();
        String role = roleComboBox.getValue();

        String lastId = userBO.getLastK().orElse("U001");

        System.out.println(lastId);
        System.out.println(username);
        System.out.println(password);
        System.out.println(confirmPassword);
        System.out.println(role);

        if (username.isEmpty() || password.isEmpty() || role.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter all the fields correctly");
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.show();
            return;
        }

        if (password.length()<8){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters");
            alert.setTitle("Password Error");
            alert.setHeaderText(null);
            alert.show();
            return;
        }

        if (!password.equals(confirmPassword)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords do not match");
            alert.setTitle("Password Error");
            alert.setHeaderText(null);
            alert.show();
            return;
        }

        if (userBO.checkUser(username)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "User already exists");
            alert.setTitle("User already exists");
            alert.setHeaderText(null);
            alert.show();
            return;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(lastId);
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setUserRole(role);

        boolean result = userBO.save(userDTO);

        if (result){
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            MainAnchorpane.getChildren().clear();
            MainAnchorpane.getChildren().add(load);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("User SignUp Failed");
            alert.show();
            return;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleComboBox.getItems().addAll("Admin","Receptionist");
    }

}