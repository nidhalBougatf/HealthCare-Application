/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Services.CrudCoach;
import Services.CrudUtilisateur;
import entities.Coach;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Rami
 */
public class Popup {
    public static void display(Coach c) throws FileNotFoundException
{
Stage popupwindow=new Stage();
      
popupwindow.initModality(Modality.APPLICATION_MODAL);
popupwindow.setTitle("Confirmation de Coach");
      
      
Label label1= new Label("Coach "+c.getNom()+" "+c.getPrenom());
label1.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
label1.setStyle("-fx-text-fill :  #0288D1 ;"); 

      
     
Button button1= new Button("Annuler");
Button AccepterCoach= new Button("Accepter Coach");
Button DesapprouverCoach= new Button("Déapprouver Coach");
button1.setStyle("-fx-background-color :   #0288D1 ; -fx-text-fill :  #ffffff ;"); 
AccepterCoach.setStyle("-fx-background-color :   #0288D1 ; -fx-text-fill :  #ffffff ;"); 
DesapprouverCoach.setStyle("-fx-background-color :   #0288D1 ; -fx-text-fill :  #ffffff ;"); 
    Label TxtNiveau = new Label(c.getNIVEAU_COACH()) ;
    TxtNiveau.setWrapText(true);
    FileInputStream ff = new FileInputStream(c.getCERTIF_COACH()) ;
    Image img = new Image(ff) ;
    ImageView imageV = new ImageView(img) ;
    imageV.setFitHeight(400);
    imageV.setFitWidth(350);
button1.setOnAction(e -> popupwindow.close());
CrudCoach cc = new CrudCoach() ;
AccepterCoach.setOnAction(e -> {
    try {
        cc.ApprouverCoach(c);
        popupwindow.close() ;
    } catch (SQLException ex) {
        Logger.getLogger(Popup.class.getName()).log(Level.SEVERE, null, ex);
    }
});
CrudUtilisateur cu = new CrudUtilisateur() ;
DesapprouverCoach.setOnAction(e -> {
    try {
        cu.BanUser(c);
            popupwindow.close() ;
    } catch (SQLException ex) {
        Logger.getLogger(Popup.class.getName()).log(Level.SEVERE, null, ex);
    }
});
     
     

VBox layout= new VBox(10);
    HBox hlayout = new  HBox() ;
    HBox hlayout2 = new  HBox() ;
    hlayout2.getChildren().addAll(imageV ,TxtNiveau) ;
    hlayout.setSpacing(20);
     hlayout2.setSpacing(40);
          hlayout2.setPadding(new Insets(20));

    hlayout.setAlignment(Pos.CENTER);
hlayout.getChildren().addAll( AccepterCoach,DesapprouverCoach , button1) ;
      
layout.getChildren().addAll(label1, hlayout2  ,hlayout);
      
layout.setAlignment(Pos.CENTER);
      
Scene scene1= new Scene(layout, 700, 550);
      
popupwindow.setScene(scene1);
      
popupwindow.showAndWait();
       
}
}
