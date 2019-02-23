/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import ControllerClient.AcceuilController;
import Services.CrudUtilisateur;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class LoginGUIController implements Initializable {
    @FXML
    private Button BtnSignup;
    @FXML
    private TextField TxtEmail;
    @FXML
    private PasswordField TxtPass;
    @FXML
    private Button BtnLogin;
    @FXML
    private Text BtnForgetPassword;
    Stage primaryStage ; 
    public static  Utilisateur CurrentUser ;
    public LoginGUIController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Connecter(ActionEvent event) throws IOException, SQLException {
       TxtEmail.setStyle(" -fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white") ; 
       TxtPass.setStyle(" -fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white") ; 

       if(TxtEmail.getText().equals("")||TxtPass.getText().equals("")){
        Alert a = new Alert(Alert.AlertType.WARNING) ; 
        a.setContentText("Veuillez inserer votre email et votre mot de pass");
        a.showAndWait();
        if(TxtEmail.getText().equals("")){
        TxtEmail.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ");
        }
        if(TxtPass.getText().equals("")){
        TxtPass.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ");
        }
        }else{
        String email = TxtEmail.getText() ; 
        String pass = TxtPass.getText() ;
            CrudUtilisateur crudutilisateur = new CrudUtilisateur() ;
            Utilisateur u = crudutilisateur.Authentification(email, pass) ; 
            
            if(u==null){
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Email ou mot de passe incorrect");
            a.showAndWait();
            }else if(u.getSTATUS()!=1){
            Alert b = new Alert(Alert.AlertType.ERROR) ; 
            b.setContentText("Compte n'est pas confirmé ou bien deactivé par l'admin");
            b.showAndWait();
            }
            else {
                CurrentUser = u ;
                if(u.getType().equals("admin")){
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Accueil.fxml")) ; 
       loader.setController(new AccueilController(primaryStage));
                
                AnchorPane root = loader.load();
        AccueilController secondController = loader.getController() ;
        
        secondController.setLabelUserName(u.getNom()+" "+u.getPrenom(),u.getId());
        Scene scene = new Scene(root);
        primaryStage.setTitle("Accueil");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true); 
        }else if(u.getType().equals("membre")){
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewClient/Acceuil.fxml")) ; 
       loader.setController(new AcceuilController(primaryStage));
                
                    BorderPane root = loader.load();
        AcceuilController secondController = loader.getController() ;
        
       // secondController.setLabelUserName(u.getNom()+" "+u.getPrenom(),u.getId());
        Scene scene = new Scene(root);
        primaryStage.setTitle("Accueil");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true); 
        }else if(u.getType().equals("coach")){
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewClient/Acceuil.fxml")) ; 
       loader.setController(new AccueilController(primaryStage));
                
                BorderPane root = loader.load();
        AccueilController secondController = loader.getController() ;
        
        secondController.setLabelUserName(u.getNom()+" "+u.getPrenom(),u.getId());
        Scene scene = new Scene(root);
        primaryStage.setTitle("Accueil");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true); 
        }else if(u.getType().equals("moderateur")){
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Accueil.fxml")) ; 
       loader.setController(new AccueilController(primaryStage));
                
                AnchorPane root = loader.load();
        AccueilController secondController = loader.getController() ;
        
        secondController.setLabelUserName(u.getNom()+" "+u.getPrenom(),u.getId());
        Scene scene = new Scene(root);
        primaryStage.setTitle("Accueil");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true); 
        }
        }
       } 
 

    }
    @FXML
     private void Signup(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Register.fxml")) ; 
       loader.setController(new RegisterController(primaryStage));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
     }
      @FXML
     private void CoachRegisterSignup(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RegisterCoach.fxml")) ; 
       loader.setController(new RegisterCoachController(primaryStage));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Register Coach");
        primaryStage.setScene(scene);
        primaryStage.show();
     }
     
   
}
 
