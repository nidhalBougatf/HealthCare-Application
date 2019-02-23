/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import controller.LoginGUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author MYTEK
 */
public class Main extends Application {
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginGUI.fxml")) ; 
       loader.setController(new LoginGUIController(primaryStage));
       
        VBox root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Health'Care");
        primaryStage.getIcons().add(new Image("/View/images/logo.png"));

        primaryStage.setScene(scene);
        primaryStage.show();
      
    }
       
    public static void main(String[] args) {
        launch() ; 
    }
    
    
}
