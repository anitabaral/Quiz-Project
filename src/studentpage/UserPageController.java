/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentpage;

import common.Common;
import db.StudentDBUtils;
import entity.Student;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ANITA
 */
public class UserPageController implements Initializable {

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
    @FXML
    private Button btnLoad;
    @FXML
    private TableView<Student> tblStudentData;
    @FXML
    private TableColumn<Student, Integer> colId;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String>colUserName;
    @FXML
    private TableColumn<Student, String>colPassword;
    @FXML
    private TableColumn<Student, Integer>colRoll;
    @FXML
    private TableColumn<Student, String> colContactNo;
    @FXML
    private TableColumn<Student, String> colDOB;
    @FXML
    private TableColumn<Student, Double> colFee;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClear;
    Student student;
    StudentDBUtils studentDBUtils;
    Common common;
    ObservableList<Student> listOfStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
            colFee.setCellValueFactory(new PropertyValueFactory<>("Fee"));

            tblStudentData.setItems(listOfStudent);

        } else {
            System.out.println("No data found ");
        }
    
    }


    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        clear();
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
       // String dob=((TextField) dateDOB.getEditor()).getText()
        //dateDOB.setText(student.getDob());
        txtFee.setText("" + student.getFee());

    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) throws SQLException {
         student = new Student();
        studentDBUtils = new StudentDBUtils();
        student.setName(txtName.getText());
        student.setUsername(txtUsername.getText());
        student.setPassword(txtPassword.getText());
        student.setRoll(Integer.parseInt(txtRoll.getText()));
        student.setContactno(txtContactNo.getText());
        String dob=((TextField) dateDOB.getEditor()).getText();
        System.out.println("DOB :"+dob);
        student.setDob(dob);
        student.setFee(Double.parseDouble(txtFee.getText()));

        if (studentDBUtils.createStudent(student)) {
            System.out.println("User Created");
            clear();
        }

    }
    

    @FXML
    private void handleUpdateButtonAction(ActionEvent event) {
         student = new Student();
        studentDBUtils = new StudentDBUtils();
        Integer id = Integer.parseInt(txtId.getText());
        //admin.setId(Integer.parseInt(txtId.getText()));
        student.setId(id);
        student.setName(txtName.getText());
        student.setUsername(txtName.getText());
        student.setPassword(txtPassword.getText());
        student.setRoll(Integer.parseInt(txtRoll.getText()));
        student.setContactno(txtContactNo.getText());
       student.setContactno(txtContactNo.getText());
       student.setFee(Double.parseDouble(txtFee.getText()));

        if (studentDBUtils.updateStudent(student)) {
            System.out.println("Admin updated Successufully");
            clear();
        }
    }
      public void clear() {
        txtName.clear();

        //txtDOB.clear();
        txtPassword.clear();
        txtUsername.clear();
        txtContactNo.clear();
        txtFee.clear();
        txtRoll.clear();
    }
    
}
