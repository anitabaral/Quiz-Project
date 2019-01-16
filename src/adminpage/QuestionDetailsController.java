/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpage;
//NOT WORKING BECAUSE THE QUESTIONS 
import common.Common;
import db.QuestionDBUtils;
import db.StudentDBUtils;
import entity.Question;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ANITA
 */
public class QuestionDetailsController implements Initializable {

    @FXML
    private TableColumn<Question,String> colId;
    @FXML
    private TableColumn<Question,String> colQuestion;
    @FXML
    private TableColumn<Question,String> colOption1;
    @FXML
    private TableColumn<Question,String> colOption2;
    @FXML
    private TableColumn<Question,String> colOption3;
    @FXML
    private TableColumn<Question,String> colOption4;
    @FXML
    private TableColumn<Question,String> colAnswer;
    QuestionDBUtils questionDBUtils;
    List<Question> listOfQuestion=new ArrayList<>();
    Question question;
    Common common;
    @FXML
    private TableView<Question> tblQuestionData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          questionDBUtils = new QuestionDBUtils();
          //question=new Question();
        listOfQuestion = questionDBUtils.fetchQuestionOfC();
        if ((listOfQuestion) != null) {

            colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colQuestion.setCellValueFactory(new PropertyValueFactory<>("Question"));
            colOption1.setCellValueFactory(new PropertyValueFactory<>("Option 1"));

            colOption2.setCellValueFactory(new PropertyValueFactory<>("Option 2"));
            colOption3.setCellValueFactory(new PropertyValueFactory<>("Option 3"));
            colOption4.setCellValueFactory(new PropertyValueFactory<>("Option 4"));
            colAnswer.setCellValueFactory(new PropertyValueFactory<>("Answer"));
           

            tblQuestionData.setItems((ObservableList<Question>) listOfQuestion);

        } else {
            System.out.println("No data found ");
        }
        
    }    
    
}
