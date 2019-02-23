/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import API.chatbot.ChatBot;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class ChatBotHelpController implements Initializable {

    @FXML
    private TextArea TxtMessage;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private VBox ChatVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Envoyer(ActionEvent event) {
        if(TxtMessage.getText().equals("")){
        Alert a = new Alert(Alert.AlertType.WARNING) ; 
        a.setContentText("Champs Vide, Veuillez ecrivez un message");
        a.showAndWait();
        }else {
            //Pane message = new Pane() ;
            Label LblMessage = new Label(TxtMessage.getText()) ;
           
            LblMessage.setStyle("-fx-background-color:  #2471A3 ;");
            LblMessage.setTextFill(Color.WHITE);
            //message.getChildren().add(LblMessage) ;
            
            LblMessage.setFont(new Font("Arial", 15));
            LblMessage.setPadding(new Insets(10, 10, 10, 10));
           
            //message.setPadding(new Insets(10, 10, 10, 10));
            LblMessage.setWrapText(true);
            ChatVBox.getChildren().add(LblMessage) ;
            ChatBot chatbot = new  ChatBot() ;
            Label LblReponse = new Label(chatbot.GetResponse(TxtMessage.getText())) ;
            
            //Pane reponse = new Pane() ;
            LblReponse.setStyle("-fx-background-color:  #2471A3 ; -fx-border-radius: 20px ; ");
            LblReponse.setTextFill(Color.WHITE);
            //reponse.getChildren().add(LblReponse) ;
            LblReponse.setFont(new Font("Arial", 15));
            
            LblReponse.setPadding(new Insets(10, 10, 10, 10));
            LblReponse.setWrapText(true);
            ChatVBox.getChildren().add(LblReponse) ;
             
    }
    }
}
