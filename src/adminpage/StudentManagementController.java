/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpage;

import common.Common;
import db.AdminDBUtils;
import db.StudentDBUtils;
import entity.Admin;
import entity.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANITA
 */
public class StudentManagementController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtContactNo;
    @FXML
    private TextField txtFee;
    @FXML
    private TextField txtRoll;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private DatePicker dateDOB;

    Student student;
    StudentDBUtils studentDBUtils;
    Common common;
    ObservableList<Student> listOfStudent;

    @FXML
    private Button btnLoad;
    @FXML
    private TableColumn<Student, Integer> colId;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String> colUserName;
    @FXML
    private TableColumn<Student, String> colPassword;
    @FXML
    private TableColumn<Student, Integer> colRoll;
    @FXML
    private TableColumn<Student, String> colContactNo;
    @FXML
    private TableColumn<?, ?> colDOB;
    @FXML
    private TableColumn<Student, Double> colFee;
    @FXML
    private TableView<Student> tblStudentData;

    /**
     * Initializes the controller class.
     */
    public void clear() {
        txtName.clear();

        //txtDOB.clear();
        txtPassword.clear();
        txtUsername.clear();
        txtContactNo.clear();
        txtFee.clear();
        txtRoll.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void handleSaveButtonAction(ActionEvent event) throws SQLException {
        student = new Student();
        studentDBUtils = new StudentDBUtils();
        student.setName(txtName.getText());
        student.setUsername(txtUsername.getText());
        student.setPassword(txtPassword.getText());
        student.setRoll(Integer.parseInt(txtRoll.getText()));
        student.setContactno(txtContactNo.getText());
        //student.setDob(dateDOB.parse(getDate()));
        student.setFee(Double.parseDouble(txtFee.getText()));

        if (studentDBUtils.createStudent(student)) {
            System.out.println("User Created");
            clear();
        }

    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        student = new Student();
        studentDBUtils = new StudentDBUtils();
        Integer id = Integer.parseInt(txtId.getText());
        if (studentDBUtils.deleteStudent(id)) {
            System.out.println("Student deleted Successfully");
            clear();
        }
    }

    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        clear();
    }

    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        student = new Student();
        studentDBUtils = new StudentDBUtils();
        listOfStudent = studentDBUtils.fetchData();
        if ((listOfStudent) != null) {

            colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            colUserName.setCellValueFactory(new PropertyValueFactory<>("Username"));

            colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
            colRoll.setCellValueFactory(new PropertyValueFactory<>("Roll"));
            colContactNo.setCellValueFactory(new PropertyValueFactory<>("Contactno"));
            colFee.setCellValueFactory(new PropertyValueFactory<>("Fee"));

            tblStudentData.setItems(listOfStudent);

        } else {
            System.out.println("No data found ");
        }
    }

    @FXML
    private void hanldeMouseClickeAction(MouseEvent event) {
        showTableDataOnFields();
    }

    void showTableDataOnFields() {
        Student student = (Student) tblStudentData.getSelectionModel().getSelectedItem();
        txtId.setText("" + student.getId());
        txtName.setText(student.getName());
        txtPassword.setText(student.getPassword());
        txtUsername.setText(student.getUsername());
        txtRoll.setText("" + student.getRoll());
        txtContactNo.setText(student.getContactno());

        txtFee.setText("" + student.getFee());

    }

}
