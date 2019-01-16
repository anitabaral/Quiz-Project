/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ANITA
 */
public class DatabaseConnector {
    public static Connection databaseConnector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizproject","root","system");
            System.out.println("Connection established");
            return connection;
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
        return null;
        
    }
    
}
