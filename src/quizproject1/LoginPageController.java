/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizproject1;

import common.Common;
import db.AdminDBUtils;
import db.StudentDBUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ANITA
 */
public class LoginPageController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button btnLogin;
    AdminDBUtils adminDBUtils;
    StudentDBUtils studentDBUtils;
    Common common;
    private String username;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("admin", "user", "play quiz");
        comboBox.setItems(list);
    }

    @FXML
    private void handleLoginButtonPressed(ActionEvent event) throws IOException {
        adminDBUtils = new AdminDBUtils();
        studentDBUtils = new StudentDBUtils();
        String userType = comboBox.getSelectionModel().getSelectedItem();
        if (userType.equals("admin")) {
            if (adminDBUtils.loginValidation(txtUsername.getText(), txtPassword.getText())) {
                common = new Common();

                common.nextStage("/adminpage/AdminPage.fxml", "AdminPage Window", true);
                Stage current = (Stage) txtUsername.getScene().getWindow();
                current.hide();
                System.out.println("Login Successful");
            }
        } else {
            if (userType.equals("user")) {

                // if (studentDBUtils.loginValidation(txtUsername.getText(), txtPassword.getText())) {
                common = new Common();
                common.nextStage("/studentpage/UserPage.fxml", "UserPage Window", true);
                Stage current = (Stage) txtUsername.getScene().getWindow();
                current.hide();
                System.out.println("Login Successful");
                //}

            } else {
                if (userType.equals("play quiz")) {

                    if (studentDBUtils.loginValidation(txtUsername.getText(), txtPassword.getText())) {
                        common = new Common();
                        common.nextStage("/quizmanagement/QuizWindow.fxml", "QuizWindow Window ", true);

                        Stage current = (Stage) txtUsername.getScene().getWindow();
                        current.hide();

                        System.out.println("Login Successful");
                    } else {
                        System.out.println("Login unsuccessful");
                    }

                }
            }
        }
    }

}
