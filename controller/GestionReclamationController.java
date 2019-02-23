/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudReclamation;
import Services.CrudUtilisateur;
import Services.MailService;
import entities.Reclamation;
import entities.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class GestionReclamationController implements Initializable {

    @FXML
    private TextField TxtSearch;
    @FXML
    private CheckBox CheckBoxTraiter;
    @FXML
    private CheckBox CheckboxNomTraitee;
    @FXML
    private TableColumn<Reclamation,Integer> ColumnUser;
    @FXML
    private TableColumn<Reclamation,String> ColumnText;
    @FXML
    private TableColumn<Reclamation,Date> ColumnDateReclamation;
    @FXML
    private TextField TxtUser;
    @FXML
    TableView<Reclamation>  TableViewReclamation ;
    @FXML
    private TextField TxtType;
    @FXML
    private TextArea TxtCommnetaire;
    @FXML
    private ComboBox<String> ComboStatus;
    @FXML
    private TextArea TxtReponse;
    ObservableList<Reclamation> data;
    CrudReclamation c; 
    int tt;
    int IdReclamation ;
    Utilisateur useselected ;
    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudReclamation c = new CrudReclamation() ;
       ColumnUser.setCellValueFactory(new PropertyValueFactory<>("ID_RECLAMATION"));
        ColumnText.setCellValueFactory(new PropertyValueFactory<>("COMMENTAIRE_R"));
        ColumnDateReclamation.setCellValueFactory(new PropertyValueFactory<>("DATE_R"));
        ComboStatus.getItems().addAll("Traitée" , "Non traité") ;
        tt=0;
        
     
        try {
            this.data = FXCollections.observableArrayList(c.displayAll());
        } catch (SQLException ex) {
            Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TableViewReclamation.setItems(data);
         
        ComboStatus.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
        if(tt==1){
            
            CrudReclamation a = new CrudReclamation() ;
           
            if(ComboStatus.getValue().equals("Traitée")){
               
                   
                try {
                     System.out.println(IdReclamation);
                     Reclamation s =c.findById(IdReclamation) ; 
                    a.ChangerStatusToTraite(s);
                data= FXCollections.observableArrayList(c.displayAll());
                          TableViewReclamation.setItems(data);
                    
                } catch (SQLException ex) {
         Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
  
        }
                
            }else if(ComboStatus.getValue().equals("Non traité")){
               
                    
                try {
                    System.out.println(IdReclamation);
                    Reclamation s =c.findById(IdReclamation) ; 
                    a.ChangerStatusToNonTraite(s);
                    data= FXCollections.observableArrayList(c.displayAll());
                    TableViewReclamation.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
        }
           
         }
            
        } 
    });
    }    

    @FXML
    private void Filtre(ActionEvent event) throws SQLException {
        
     String typ = "''";
         if(CheckBoxTraiter.isSelected()&&CheckboxNomTraitee.isSelected()){
         CrudReclamation CA = new CrudReclamation() ;
            ObservableList Obchercher = FXCollections.observableArrayList(CA.displayAll()); 
         
       TableViewReclamation.setItems(Obchercher);
         }
         else{
        if(CheckBoxTraiter.isSelected() ){typ+=" OR ETAT_R='traite'" ;} 
        if(CheckboxNomTraitee.isSelected() ){typ+=" OR type ETAT_R='nontraite'" ;} 
        
        

            CrudReclamation CA = new CrudReclamation() ;
            ObservableList Obchercher = FXCollections.observableArrayList(CA.SearchReclamationByStatus(typ)); 
         
       TableViewReclamation.setItems(Obchercher);
    }   
    }


    private void init() {
               TableViewReclamation.setItems(data);

    }

    @FXML
    private void EnvoyerMail(ActionEvent event) {
        if(TxtReponse.getText().equals("")){
        Alert a = new Alert(Alert.AlertType.ERROR) ; 
        a.setContentText("rien a envoyer");
        a.showAndWait();
        
        }else{
            MailService s = new MailService() ; 
            try {
                s.SendReclamation(useselected, TxtReponse.getText());
            } catch (MessagingException ex) {
        Alert a = new Alert(Alert.AlertType.ERROR) ; 
        a.setContentText("Erreur dans l'envoi du mail");
        a.showAndWait();
            }
        }
        
    }
    
    @FXML
    private void SelectData(MouseEvent event) throws SQLException {
        Reclamation r = TableViewReclamation.getSelectionModel().getSelectedItem() ;
                System.out.println(TableViewReclamation.getSelectionModel().getSelectedItem());
            
        CrudUtilisateur a = new CrudUtilisateur() ;
        Utilisateur u = a.FindUserById(r.getID_UTILISATEUR()) ;
        TxtUser.setText(u.getNom()+" "+u.getPrenom());
        TxtType.setText(r.getTYPE_R());
        TxtCommnetaire.setText(r.getCOMMENTAIRE_R());
        if(r.getETAT_R().equals("traite")){
        ComboStatus.getSelectionModel().selectFirst();
        }else if(r.getETAT_R().equals("nontraite")){
        ComboStatus.getSelectionModel().select(1);
        }
        
        this.IdReclamation = r.getID_RECLAMATION() ;
        TxtReponse.setText(" Cher client "+u.getNom() +  " "+ u.getPrenom()+", nous avons bien reçu votre courrier du "+ r.getDATE_R() +"et nous vous remercions de vous être adressé à nous.");
        useselected=u;
        this.tt=1;
    }
     @FXML
    private void CheckTableView(ActionEvent event) throws SQLException {
        if(CheckBoxTraiter.isSelected()){
            System.out.println("Traiter checked");
        }
     if(CheckboxNomTraitee.isSelected()){
            System.out.println("NonTraiter checked");
        }}

    @FXML
    private void Search(ActionEvent event) throws SQLException {
    System.out.println("seaaaaaaaaaaaarch");
          if(TxtSearch.getText()==null){
      init() ;
       }
       else{    
              CrudReclamation p = new CrudReclamation() ;
             List<Reclamation> chercherlist = new ArrayList<>() ;
       chercherlist= p.SearchReclamation(TxtSearch.getText());
       ObservableList Obchercher = FXCollections.observableArrayList(chercherlist); 
            
       TableViewReclamation.setItems(Obchercher);
       }
    }
    
}
