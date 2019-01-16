/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpage;

import common.Common;
import db.AdminDBUtils;
import entity.Admin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANITA
 */
public class AdminController implements Initializable {

    @FXML
    private GridPane cmbSecurityQuestion;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtSecurityAnswer;
    @FXML
    private ComboBox<String> cmbSecurityQuestions;
    @FXML
    private TableView<Admin> tblAdminData;
    @FXML
    private TableColumn<Admin, Integer> colId;
    @FXML
    private TableColumn<Admin, String> colAdminName;
    @FXML
    private TableColumn<Admin, String> colPassword;
    @FXML
    private TableColumn<Admin, String> colSecurityQuestion;
    @FXML
    private TableColumn<Admin, String> colSecurityAnswer;
    private Admin admin;
    private AdminDBUtils adminDBUtils;
    ObservableList<Admin> listOfAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("What is your favorite food ?", "What is your Favorite Sports ?");
        cmbSecurityQuestions.setItems(list);
    }

    @FXML
    private void handleStudentManagementAction(ActionEvent event) throws IOException {
        Common common = new Common();
        common.nextStage("/adminpage/StudentManagement.fxml", "Student Management Window ", true);

        Stage current = (Stage) txtPassword.getScene().getWindow();
        current.hide();
        
    }

    @FXML
    private void handleStudentDetailsAction(ActionEvent event) throws IOException {
        Common common = new Common();
        common.nextStage("/adminpage/StudentDetails.fxml", "Student Details Window ", true);

        Stage current = (Stage) txtPassword.getScene().getWindow();
        current.hide();
        
    }

    @FXML
    private void handleQuestionManagementAction(ActionEvent event) {
    }

    @FXML
    private void handleQuestionListsAction(ActionEvent event) throws IOException {
        Common common = new Common();
        common.nextStage("/adminpage/QuestionDetails.fxml", "Question Details Window ", true);

        Stage current = (Stage) txtPassword.getScene().getWindow();
        current.hide();
    }

    @FXML
    private void hanldeResultAction(ActionEvent event) {
    }

    @FXML
    private void hanldeReportAction(ActionEvent event) {
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        admin = new Admin();
        adminDBUtils = new AdminDBUtils();
        String securityQuestion = cmbSecurityQuestions.getSelectionModel().getSelectedItem();

        admin.setAdminName(txtName.getText());
        admin.setPassword(txtPassword.getText());
        admin.setSecurityQuestion(securityQuestion);
        admin.setSecurityAnswer(txtSecurityAnswer.getText());

        if (adminDBUtils.createAdmin(admin)) {
            System.out.println("Admin inserted Successfully");
            clear();
        }
    }

    @FXML
    private void hanldeUpdateAction(ActionEvent event) {
        admin = new Admin();
        adminDBUtils = new AdminDBUtils();
        Integer id = Integer.parseInt(txtId.getText());
        String securityQuestion = cmbSecurityQuestions.getSelectionModel().getSelectedItem();
        //admin.setId(Integer.parseInt(txtId.getText()));
        admin.setId(id);
        admin.setAdminName(txtName.getText());
        admin.setPassword(txtPassword.getText());
        admin.setSecurityQuestion(securityQuestion);
        admin.setSecurityAnswer(txtSecurityAnswer.getText());

        if (adminDBUtils.updateAdmin(admin)) {
            System.out.println("Admin updated Successufully");
            clear();
        }
    }

    @FXML
    private void hanldeMouseClickeAction(MouseEvent event) {
        showTableDataOnFields();
    }

    void showTableDataOnFields() {
        Admin admin = (Admin) tblAdminData.getSelectionModel().getSelectedItem();
        txtId.setText("" + admin.getId());
        txtName.setText(admin.getAdminName());
        txtPassword.setText(admin.getPassword());
        cmbSecurityQuestions.setValue(admin.getSecurityQuestion());
        txtSecurityAnswer.setText(admin.getSecurityAnswer());
    }

    @FXML
    private void hanldeDeleteAction(ActionEvent event) {
         admin = new Admin();
        adminDBUtils = new AdminDBUtils();
        Integer id = Integer.parseInt(txtId.getText());
          if (adminDBUtils.deleteAdmin(id)) {
            System.out.println("Admin deleted Successfully");
            clear();
        }
    }

    @FXML
    private void hanldeLoadAction(ActionEvent event) {
        admin = new Admin();
        adminDBUtils = new AdminDBUtils();
        listOfAdmin = adminDBUtils.fetchData();
        if ((listOfAdmin) != null) {
            colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colAdminName.setCellValueFactory(new PropertyValueFactory<>("AdminName"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
            colSecurityQuestion.setCellValueFactory(new PropertyValueFactory<>("SecurityQuestion"));
            colSecurityAnswer.setCellValueFactory(new PropertyValueFactory<>("SecurityAnswer"));

            tblAdminData.setItems(listOfAdmin);

        } else {
            System.out.println("No data found ");
        }
    }

    @FXML
    private void hanldeLogoutAction(ActionEvent event) throws IOException {

        Common common = new Common();
        common.nextStage("/quizproject1/LoginPage.fxml", "Login Window ", true);

        Stage current = (Stage) txtPassword.getScene().getWindow();
        current.hide();

    }

    private void clear() {
        txtId.clear();
        txtName.clear();
        txtPassword.clear();
        txtSecurityAnswer.clear();
        cmbSecurityQuestions.getSelectionModel().clearSelection();
    }

}
