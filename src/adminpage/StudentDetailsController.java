/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpage;

import common.Common;
import db.StudentDBUtils;
import entity.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ANITA
 */
public class StudentDetailsController implements Initializable {

    
   
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
    Student student;
    StudentDBUtils studentDBUtils;
    Common common;
    ObservableList<Student> listOfStudent;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  student = new Student();
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
    
}
