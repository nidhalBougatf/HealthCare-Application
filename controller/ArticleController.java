/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudArticle;
import entities.Article;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author CePc
 */
public class ArticleController implements Initializable {

    
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField editeur;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField affiche;
    @FXML
    private TextField chercher;
    
    @FXML
    private TableColumn<Article, String> col_titre;
    @FXML
    private TableColumn<Article, String> col_description;
    @FXML
    private TableColumn<Article, String> col_edit;
    @FXML
    private TableColumn<Article, String> col_type;
    @FXML
    private TableColumn<Article, Date> col_Date;
    private ObservableList<Article> list_Article,AllArticle_List ;
    @FXML
    private TableView<Article> tableview;
    @FXML
    private TableColumn<Article, Integer> col_IdArticle;
    @FXML
    private TableColumn<Article, Integer> col_IdUtil;
     @FXML
    private ImageView image;
    @FXML
    private TableColumn<?, ?> col_Affiche;
    
     
    @FXML
    private javafx.scene.control.Button annuler;
    
    @FXML
    private javafx.scene.control.Button enregistrer;
   
    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private CheckBox Sport;
    @FXML
    private CheckBox Santé;
    @FXML
    private CheckBox Médecine;
    @FXML
    private CheckBox Nutrition;
    @FXML
    private CheckBox Thérapie;
    @FXML
    private CheckBox Horoscope;
    @FXML
    private CheckBox Citation;
    @FXML
    private CheckBox Psychologie;
    @FXML
    private CheckBox Motivation;
    
    private int idHolder;
    private boolean selected=false;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Modifier;
    @FXML
    private Button supprimer;
    
    
    CrudArticle CA=new CrudArticle();
    Alert alert=new Alert(Alert.AlertType.WARNING);
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String>  ComboList = FXCollections.observableArrayList("Sport","Santé","Nutrition","Médecine","Thérapie","Horoscope","Citation","Psychologie","Motivation");
        type.setItems(ComboList);
        
         annuler.setVisible(false);
        enregistrer.setVisible(false);
        
       buildArticleTable() ;
        
        try {
            tableview.setItems(CA.displayAllArticle())  ;
        } catch (SQLException ex) {
           
        }
     tableview.setEditable(true);

     tableview.getSelectionModel().selectedItemProperty().addListener(
(
ObservableValue<? extends Article> observable,Article oldValue,Article newValue) -> {
if (newValue == null) {
return;
}
getArticleInfo(newValue.getID_ARTICLE());
});

    }    

      
      @FXML
    private void buildArticleTable()  {
       
         Article a=new Article(1,
                 titre.getText(),
                 description.getText(),
                 editeur.getText(),
                 type.getValue(),
                 affiche.getText(),
                 Date.valueOf(LocalDate.now()));

        list_Article =FXCollections.observableArrayList();
   
         col_titre.setCellValueFactory(new PropertyValueFactory<>("TITRE_A"));
         col_description.setCellValueFactory(new PropertyValueFactory<>("DESCRIPTION_A"));
         col_edit.setCellValueFactory(new PropertyValueFactory<>("EDITEUR_A"));
         col_type.setCellValueFactory(new PropertyValueFactory<>("TYPE_A"));
         col_Date.setCellValueFactory(new PropertyValueFactory<>("Date_a"));
         col_IdArticle.setCellValueFactory(new PropertyValueFactory<>("ID_ARTICLE"));
         col_IdUtil.setCellValueFactory(new PropertyValueFactory<>("ID_UTILISATEUR"));
         col_Affiche.setCellValueFactory(new PropertyValueFactory<>("AFFICHE_A"));
         
           tableview.getItems().clear();
           
         tableview.getItems().addAll(list_Article);
        try {
            CA.displayAllArticle();
            CA.UpdateArticle(a);
        } catch (SQLException ex) {
           
        }
        
    }
    
 
    @FXML
    private void Upload(ActionEvent event) throws SQLException  {
            Article a=new Article(1, titre.getText(), description.getText(), editeur.getText(), type.getValue(), affiche.getText().substring(idHolder, idHolder), Date.valueOf(LocalDate.now()));

       
            FileChooser fileChooser = new FileChooser();
           FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
       
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            
           
             if (affiche.equals("")) 
        {
             
            alert.setContentText("Veuilez selectionner une affiche !!");
            alert.showAndWait();
        }
        else
        {
            affiche.setText(file.getAbsolutePath());
        }
        Image img = new Image("file:"+file.getAbsolutePath());
        image.imageProperty().set(img);
            CA.UpdateArticle(a);
        

     
         
            

        
    }

    
    

  
    
    

   
    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        if((titre.getText().equals(""))||(description.getText().equals(""))||(editeur.getText().equals(""))||(type.getValue().equals("Type"))||(affiche.getText().equals(""))){
            alert.setTitle("Error Dialog");
            alert.setContentText("Veuillez saisir tout les CHAMPS ! ");
            alert.showAndWait();
        }
         else
        {

            Article a=new Article(1, titre.getText(), description.getText(), editeur.getText(), type.getValue(), affiche.getText(), Date.valueOf(LocalDate.now()));
            a.setID_UTILISATEUR(1);
            a.setTITRE_A(titre.getText());
            a.setDESCRIPTION_A(description.getText());
            a.setEDITEUR_A(editeur.getText());
            a.setTYPE_A(type.getValue());
            a.setAFFICHE_A(affiche.getText());
            
            CA.insertSt(a);
            tableview.getItems().add(a);
            titre.clear();
            description.clear();
            editeur.clear();
            affiche.clear();
            alert.setTitle("Succes Dialog");
            alert.setContentText("Article ajouté avec succés !!");
            alert.showAndWait();

  
    }}
    
    

   

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        
        Alert alertconfirm=new Alert(Alert.AlertType.CONFIRMATION);
        alertconfirm.setTitle("Confirmation de la supression");
        alertconfirm.setHeaderText(null);
        alertconfirm.setContentText("êtes vous sure de supprimer l'article ");
        Optional <ButtonType> action =alertconfirm.showAndWait();
        if(action.get()==ButtonType.OK){
            try{
        AllArticle_List=tableview.getItems();
        list_Article=tableview.getSelectionModel().getSelectedItems();
        list_Article.forEach(AllArticle_List::remove);
         CA.DeleteSt(tableview.getSelectionModel().getSelectedItem().getID_ARTICLE());
         
            }
            catch(SQLException ex){
                System.out.println(ex);
        
            }
        }
       
        
        
        
    }
    
   

    @FXML
    private void Rechercher(ActionEvent event) {
         try {
            
            tableview.setItems(CA.searchByNameArticle(chercher.getText()))  ;
           
            
        } catch (SQLException ex) {
           
        }
        
    }

    @FXML
    private void Filtrer(ActionEvent event) throws SQLException {
   
         String typ = "''";
         
        if(Sport.isSelected() ){typ+=" OR TYPE_A LIKE 'Sport'" ;} 
        if(Santé.isSelected()){typ+=" OR TYPE_A LIKE 'Santé'";} 
        if(Médecine.isSelected()){typ+=" OR TYPE_A LIKE 'Médecine' ";}
        if(Nutrition.isSelected()){typ+=" OR TYPE_A LIKE 'Nutrition' ";}
        if(Thérapie.isSelected() ){typ+=" OR TYPE_A LIKE 'Thérapie'" ;} 
        if(Horoscope.isSelected()){typ+=" OR TYPE_A LIKE 'Horoscope'";} 
        if(Citation.isSelected()){typ+=" OR TYPE_A LIKE 'Citation' ";}
        if(Psychologie.isSelected()){typ+=" OR TYPE_A LIKE 'Psychologie' ";}
        if(Motivation.isSelected()){typ+=" OR TYPE_A LIKE 'Motivation' ";}

    
            tableview.setItems(CA.searchByType(typ))  ;
           
        
                
                
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
     Article a=new Article(1, titre.getText(), description.getText(), editeur.getText(), type.getValue(), affiche.getText(), Date.valueOf(LocalDate.now()));
     
       if (selected == false) {
Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Aucun objet selectionné");
alert.setContentText("Veuillez selectionner un Article à modifier !");
alert.showAndWait();
          
} 
else 
{
            annuler.setVisible(true);
            enregistrer.setVisible(true);
            Modifier.setVisible(false);
            Ajouter.setVisible(false);
            supprimer.setVisible(false);
      

    if(!(titre.getText().isEmpty()&&description.getText().isEmpty()&&editeur.getText().isEmpty()&&affiche.getText().isEmpty())){
        {
              
  CA.UpdateArticle(a);
        }
    }
    else{


      Article result = (Article) CA.searchByID_Article(idHolder);
tableview.getSelectionModel();
titre.setText(result.getTITRE_A()); 
description.setText(result.getDESCRIPTION_A());
editeur.setText(result.getEDITEUR_A());
type.setValue(result.getTYPE_A());

            

affiche.setText(result.getAFFICHE_A());
        Image img = new Image("file:"+affiche.getText());
        image.imageProperty().set(img);
        

} }
    }
    
    @FXML
    private void enregistrer(ActionEvent event) throws SQLException {
        Article a=new Article(idHolder,1, titre.getText(), description.getText(), editeur.getText(), type.getValue(), affiche.getText(), Date.valueOf(LocalDate.now()));
           if((titre.getText().equals(""))||(description.getText().equals(""))||(editeur.getText().equals(""))||(type.getValue().equals("Type"))||(affiche.getText().equals(""))){
            alert.setContentText("Veuillez saisir tout les CHAMPS ! ");
            alert.showAndWait();
        }
         else
        {
         
            a.setID_UTILISATEUR(1);
            a.setTITRE_A(titre.getText());
            a.setDESCRIPTION_A(description.getText());
            a.setEDITEUR_A(editeur.getText());
            a.setTYPE_A(type.getValue());
            a.setAFFICHE_A(affiche.getText());

        try {
            CA.UpdateArticle(a);
            tableview.setItems(CA.displayAllArticle());
            
            annuler.setVisible(false);enregistrer.setVisible(false);Modifier.setVisible(true);
            Ajouter.setVisible(true);supprimer.setVisible(true);image.setImage(null);
        
        
            
            titre.clear();
            description.clear();
            editeur.clear();
            affiche.clear();
            
            alert.setContentText("Article Mis à jour avec succés !!");
            alert.showAndWait();

        } catch (SQLException ex) {
           
        }
         
        }
                
                       
                      
    
   
    }
    @FXML
     public void annuler (ActionEvent event)
    {
        titre.clear();
        description.clear();
        editeur.clear();
        affiche.clear();
        annuler.setVisible(false);
        enregistrer.setVisible(false);
        Modifier.setVisible(true);
        Ajouter.setVisible(true);
        supprimer.setVisible(true);
        image.imageProperty().setValue(null);
        
    }

 


private void getArticleInfo(Integer id_Article) 
{
selected = true;
idHolder = id_Article;
    
}

    

    
    
}
