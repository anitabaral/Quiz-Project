/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Admin;
import entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ANITA
 */
public class StudentDBUtils {

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection = DatabaseConnector.databaseConnector();
    String username;
    ObservableList<Student> listOfStudent = FXCollections.observableArrayList();

    public boolean createStudent(Student student) throws SQLException {
        if (connection != null) {
            String query = "INSERT INTO User (Name,UserName,Password,Roll,ContactNo,DOB,Fee) VALUES (?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getUsername());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setInt(4, student.getRoll());
            preparedStatement.setString(5, student.getContactno());
            preparedStatement.setString(6,  student.getDob());
            preparedStatement.setDouble(7, student.getFee());

            preparedStatement.execute();
            connection.close();
            return true;

        }
        return false;
    }
//    public Boolean insertMarks(int marks,String username)
//    {
//        if(connection!=null){
//            try{
//                
//            }
//        }
//    }

    public Boolean loginValidation(String username, String password) {
        if (connection != null) {
            try {
                System.out.println("USERNAME : " + username);
                System.out.println("PASSWORD : " + password);
                String query = "SELECT * FROM user WHERE UserName=? AND Password=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return true;
                }
            } catch (SQLException e) {
                Logger.getLogger(StudentDBUtils.class.getName()).log(Level.SEVERE, null, e);

            }

        }

        return false;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ObservableList<Student> fetchData() {
        try {
            String query = "SELECT * FROM User";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);

            while (resultSet.next()) {

                Integer id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String username = resultSet.getString("UserName");
               
                String password = resultSet.getString("Password");
                
                Integer roll = resultSet.getInt("Roll");

                String contactno = resultSet.getString("ContactNo");
                //String dob = resultSet.getString("DOB");
                String dob=resultSet.getString("DOB");
                Double fee = resultSet.getDouble("Fee");
          
                
                Student student = new Student(id,name,username,password,roll, contactno,dob,fee);
                listOfStudent.add(student);

            }
            return listOfStudent;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public boolean deleteStudent(int id) {
        try {
            if (connection != null) {
                String query = "DELETE FROM User WHERE Id=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean updateStudent(Student student){
     if (connection != null) {
            String query = "UPDATE Student Set Name=?,UserName=?,Password=?,Roll=?,ContactNo=?,DOB=?,Fee=? WHERE Id=?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, student.getName());
               preparedStatement.setString(1, student.getUsername());
               preparedStatement.setString(1, student.getPassword());
               preparedStatement.setInt(1, student.getRoll());
               preparedStatement.setString(1, student.getContactno());
              // preparedStatement.setString(1, student.getName());
               preparedStatement.setDouble(1, student.getFee());
               

                preparedStatement.executeUpdate();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
}

}