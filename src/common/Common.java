/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author ANITA
 */
public class Common {
    public void nextStage(String fxml,String title,boolean resizable)throws IOException{
        Parent window =FXMLLoader.load(getClass().getResource(fxml));
        Stage stage=new Stage();
        Scene scene=new Scene(window);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.show();
        
    }
    
}
