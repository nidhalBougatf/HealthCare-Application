/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudCoach;
import Services.CrudUtilisateur;
import entities.Coach;
import entities.Utilisateur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Popup;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class GestionMembreController implements Initializable {
    @FXML
    private TableView<Utilisateur> TablViewList;
    @FXML
    private TableColumn<Utilisateur, String> UserNameColumn;
    @FXML
    private TableColumn<Utilisateur, String> EmailColumn;
    @FXML
    private TableColumn<Utilisateur, String> StatusColumn;
    @FXML
    private TableColumn<Utilisateur, String> TypeColumn;
    @FXML
    private TableColumn<Utilisateur, String> UserLastNameColumn;
    
    @FXML
    private TextField TxtSearch;
    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtGender;
    @FXML
    private TextField TxtNumtel;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtType;
    CrudUtilisateur c = new CrudUtilisateur() ;
    ObservableList<Utilisateur> data;
    @FXML
    private VBox CoachLayout;
    @FXML
    private CheckBox ChechkBoxModerateur;
    @FXML
    private CheckBox ChechkBoxCoachs;
    @FXML
    private CheckBox ChechkBoxMembres;
    @FXML
    private ImageView ImageView;
   
    
    public GestionMembreController() {
        try {
            this.data = FXCollections.observableArrayList(c.displayAllUsers());
        } catch (SQLException ex) {
            Logger.getLogger(GestionMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        UserLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("StatusToString"));
        
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TablViewList.setItems(data);
        CoachLayout.setVisible(false);
    }    

    @FXML
    private void SelectData(MouseEvent event) throws SQLException, FileNotFoundException {
        CoachLayout.setVisible(false);
        Utilisateur user = TablViewList.getSelectionModel().getSelectedItem() ;
        TxtNom.setText(user.getNom());
        TxtPrenom.setText(user.getPrenom());
        TxtGender.setText(user.getGender());
        TxtNumtel.setText(user.getNum_tel()+"");
        TxtEmail.setText(user.getEmail());
        TxtType.setText(user.getType());
        if(user.getSTATUS()==3){
         CoachLayout.setVisible(true);
         CrudCoach cr = new CrudCoach() ; 
         Coach k =cr.FindCoachById(user.getId()) ;
         FileInputStream f = new FileInputStream(k.getCERTIF_COACH()) ;  
         Image image = new Image(f);
         ImageView.setImage(image);
        }
        
    }

    @FXML
    private void BanUser(ActionEvent event) throws SQLException {
        CrudUtilisateur c = new CrudUtilisateur() ;
        if(TablViewList.getSelectionModel().getSelectedItem()==null){
        Alert a = new Alert(Alert.AlertType.WARNING) ; 
        a.setContentText("Selectionnez un utilisateur");
        a.showAndWait();
        }else{
        c.BanUser(TablViewList.getSelectionModel().getSelectedItem());
        ObservableList data1 = FXCollections.observableArrayList(c.displayAllUsers());
        TablViewList.setItems(data1);
        }
    }
    @FXML
    private void UnbannUser(ActionEvent event) throws SQLException {
        CrudUtilisateur c = new CrudUtilisateur() ;
        if(TablViewList.getSelectionModel().getSelectedItem()==null){
        Alert a = new Alert(Alert.AlertType.WARNING) ; 
        a.setContentText("Selectionnez un utilisateur");
        a.showAndWait();
        }else{
        c.UnBanUser(TablViewList.getSelectionModel().getSelectedItem());
                    
        ObservableList data1 = FXCollections.observableArrayList(c.displayAllUsers());

        TablViewList.setItems(data1);

        }
    }

    @FXML
    private void Filtre(KeyEvent event) throws SQLException {
         if(TxtSearch.getText()==null){
      init() ;
       }
       else{
             List<Utilisateur> chercherlist = null ;
       chercherlist = c.FindUserByNameOrLastname(TxtSearch.getText());
       ObservableList Obchercher = FXCollections.observableArrayList(chercherlist); 
            
       TablViewList.setItems(Obchercher);
       }
    }


    private void init() {
               TablViewList.setItems(data);

    }

    @FXML
    private void AddModerateur(ActionEvent event) throws SQLException {
       CrudUtilisateur c = new CrudUtilisateur() ;
        if(TablViewList.getSelectionModel().getSelectedItem()==null){
        Alert a = new Alert(Alert.AlertType.WARNING) ; 
        a.setContentText("Selectionnez un utilisateur");
        a.showAndWait();
        }else{
        c.UpdateMembreToModerateur(TablViewList.getSelectionModel().getSelectedItem());
                    
        ObservableList data1 = FXCollections.observableArrayList(c.displayAllUsers());

        TablViewList.setItems(data1);

        }
    }

    @FXML
    private void AccepterCoachInvi(ActionEvent event) throws SQLException {
        CrudUtilisateur c = new CrudUtilisateur();
        if(TablViewList.getSelectionModel().getSelectedItem()!=null){
        
        c.ApprouverCoach(TablViewList.getSelectionModel().getSelectedItem());
                    
        ObservableList data1 = FXCollections.observableArrayList(c.displayAllUsers());

        TablViewList.setItems(data1);

        }
        CoachLayout.setVisible(false);
    }

    @FXML
    private void AnnulerCoachinvi(ActionEvent event) throws SQLException {
         CrudUtilisateur c = new CrudUtilisateur() ;
        if(TablViewList.getSelectionModel().getSelectedItem()!=null){
            c.BanUser(TablViewList.getSelectionModel().getSelectedItem());
        ObservableList data1 = FXCollections.observableArrayList(c.displayAllUsers());
        TablViewList.setItems(data1);
        
    }
        CoachLayout.setVisible(false);
    }

    @FXML
    private void CheckFiltre(ActionEvent event) throws SQLException {
        String typ = "''";
         
        if(ChechkBoxModerateur.isSelected() ){typ+=" OR type LIKE 'moderateur'" ;} 
        if(ChechkBoxCoachs.isSelected() ){typ+=" OR type LIKE 'coach'" ;} 
        if(ChechkBoxMembres.isSelected() ){typ+=" OR type LIKE 'membre'" ;} 
        

            CrudUtilisateur CA = new CrudUtilisateur() ;
            ObservableList Obchercher = FXCollections.observableArrayList(CA.displayUsersByType(typ)); 
            
       TablViewList.setItems(Obchercher);
    }

    @FXML
    private void AfficherCoachDetails(MouseEvent event) throws SQLException, FileNotFoundException{
        Utilisateur user = TablViewList.getSelectionModel().getSelectedItem() ;
        CrudCoach c = new CrudCoach() ;
        CrudUtilisateur cu = new CrudUtilisateur() ; 
        Popup p = new Popup() ;
        p.display(c.FindCoachById(user.getId()));
        ObservableList data1 = FXCollections.observableArrayList(cu.displayAllUsers());
        TablViewList.setItems(data1);
}
}
