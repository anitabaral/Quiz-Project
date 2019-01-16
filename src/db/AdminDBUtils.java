/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ANITA
 */
public class AdminDBUtils {

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection = DatabaseConnector.databaseConnector();
    ObservableList<Admin> listOfAdmin = FXCollections.observableArrayList();

    public boolean createAdmin(Admin admin) {
        if (connection != null) {
            String query = "INSERT INTO Admin(AdminName,Password,SecurityQuestion,SecurityAnswer)VALUES (?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, admin.getAdminName());
                preparedStatement.setString(2, admin.getPassword());
                preparedStatement.setString(3, admin.getSecurityQuestion());
                preparedStatement.setString(4, admin.getSecurityAnswer());
                preparedStatement.execute();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Boolean loginValidation(String username, String password) {
        if (connection != null) {
            try {
                String query = "SELECT * FROM Admin WHERE AdminName=? AND Password=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return true;
                }
            } catch (SQLException e) {
                Logger.getLogger(AdminDBUtils.class.getName()).log(Level.SEVERE, null, e);

            }

        }

        return false;

    }

    public ObservableList<Admin> fetchData() {
        try {
            String query = "SELECT * FROM Admin";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);

            while (resultSet.next()) {

                Integer id = resultSet.getInt("Id");
                String adminName = resultSet.getString("AdminName");
                String password = resultSet.getString("Password");
                String securityQuestion = resultSet.getString("SecurityQuestion");
                String securityAnswer = resultSet.getString("SecurityAnswer");

                Admin admin = new Admin(id, adminName, password, securityQuestion, securityAnswer);
                listOfAdmin.add(admin);

            }
            return listOfAdmin;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public boolean updateAdmin(Admin admin) {
        if (connection != null) {
            String query = "UPDATE Admin Set AdminName=?,Password=?,SecurityQuestion=?,SecurityAnswer=? WHERE Id=?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, admin.getAdminName());
                preparedStatement.setString(2, admin.getPassword());
                preparedStatement.setString(3, admin.getSecurityQuestion());
                preparedStatement.setString(4, admin.getSecurityAnswer());
                preparedStatement.setInt(5, admin.getId());

                preparedStatement.executeUpdate();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean deleteAdmin(int id) {
        try {
            if (connection != null) {
                String query = "DELETE FROM Admin Where Id=?";
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

}
