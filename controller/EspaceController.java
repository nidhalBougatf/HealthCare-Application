/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entite.Espace;
import Services.CrudEspace;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Karray
 */
public class EspaceController implements Initializable {
    @FXML
    TableView<Espace> table;

    @FXML
    private TableColumn<Espace, String> nomcl;
    @FXML
    private TableColumn<Espace, String> adressecl;
    @FXML
    private TableColumn<Espace, String> mailcl;
    @FXML
    private TableColumn<Espace, String> NumCl;
    @FXML
    private TableColumn<Espace, String> facebookcl;
    @FXML
    private TableColumn<Espace, String> logocl;
    @FXML
    private TableColumn<Espace, String> typecl;
    @FXML
    private TableColumn<Espace, String> idEspacecl;
    @FXML
    private Button Deletebtn;
    @FXML
    private TextField idtf;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField adressetf;
    @FXML
    private TextField mailtf;
    @FXML
    private TextField numtel;
    @FXML
    private TextField logotf;
    @FXML
    private TextField typetf;
    @FXML
    private TextField facebooktf;
    @FXML
    private Button modifbtn;
    @FXML
    private Button ajoutbtn;
    @FXML
    private TextField searchtf;

    
    final ObservableList<String> ComboType = FXCollections.observableArrayList();              //classes d'affichages JavaFX qui ont besoin d'être informées de tous les changements faits sur la liste
    public ImageView imageView;
    public Image i;
    @FXML
    private Button chercherbtn;
    @FXML
    private VBox VboxInterfaceSuppModif;
    @FXML
    private VBox VboxInterfaceAjout;
    @FXML
    private ImageView ImLogo;
    @FXML
    private VBox VboxInterfaceAjoutCours;
    @FXML
    private Button addCoursbtn;
    
    
   
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CrudEspace ser = new CrudEspace();
        setCelluleValue();
      
    
        try {
            table.getItems().addAll(ser.displayAllObs());
        } catch (SQLException ex) {
            Logger.getLogger(EspaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
           table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
              Espace SelectedEspace = table.getItems().get(table.getSelectionModel().getSelectedIndex());
              nomtf.setText(SelectedEspace.getNomEspace());
              adressetf.setText(SelectedEspace.getADRESSE_ES());
              mailtf.setText(SelectedEspace.getEMAIL_ES());
              
              logotf.setText(SelectedEspace.getLOGO_ES());
              i = new Image("file:"+SelectedEspace.getLOGO_ES() );
        ImLogo.imageProperty().set(i);
              typetf.setText(SelectedEspace.getTYPE_ES());
              idtf.setText(SelectedEspace.getID_ESPACESTR());
              numtel.setText(SelectedEspace.getNUMTEL_ESStr());
              facebooktf.setText(SelectedEspace.getFACEBOOK_ES());
             // facebook
            }
        });
       
       
            
        
               
        
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        ObservableList<Espace> allEspaces,SelectedEspace;
        allEspaces=table.getItems();
        SelectedEspace=table.getSelectionModel().getSelectedItems();
        System.out.println(SelectedEspace);
        
        SelectedEspace.forEach(allEspaces::remove);
        int x = Integer.parseInt(idtf.getText());
         CrudEspace ser = new CrudEspace();
        /* if(nomtf.getText().isEmpty())
         {
             
             AlertBox.display("jkj", "jkjhkl");
         }*/
         ser.deleteST(x);
         clearFields();
        
        
        
        
        
    }
   

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Espace P= new Espace (Integer.parseInt(idtf.getText()),nomtf.getText(),adressetf.getText(),mailtf.getText(),Integer.parseInt(numtel.getText()),facebooktf.getText(),logotf.getText(),typetf.getText());
      CrudEspace ser = new CrudEspace();
      ser.updateST(P);
      clearFields();
      refreshTable();
     

    
    
    }
        @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        VboxInterfaceSuppModif.setVisible(false);
        
         VboxInterfaceAjout.setVisible(true);
        Espace p = new Espace();
        
         Label Nomlabel = new Label();    
        TextField nomtf = new TextField();
        nomtf.setPromptText("Nom espace");
        HBox ch1 = new HBox(5);
        ch1.setPadding(new Insets(2,2,2,2));
        ch1.getChildren().addAll(nomtf); 
        
        
        Label Adresselabel = new Label();
        TextField Adressetf = new TextField();
        Adressetf.setPromptText("Adresse");
        HBox ch2 = new HBox(34);
        ch2.setPadding(new Insets(2,2,2,2));
        ch2.getChildren().addAll(Adressetf); 
        
        Label Maillabel = new Label();
        TextField Mailtf = new TextField();
        Mailtf.setPromptText("E-mail");
        HBox ch3 = new HBox(44);
        ch3.setPadding(new Insets(2,2,2,2));
        ch3.getChildren().addAll(Mailtf); 
        
        Button Uploadbtn = new Button("Upload");
         Label Logolabel = new Label();
        TextField Logotf = new TextField();
        Logotf.setMaxWidth(150);
        Logotf.setPromptText("Logo");
        imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        Uploadbtn.setOnAction(e->{ 
            //**************************************************IMAGE
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir l'affiche");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String pathh = selectedFile.getAbsolutePath();
        
        if (pathh.equals("")) 
        {
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Veuilez selectionner une affiche !!");
            a.showAndWait();
        }
        else
        {
            Logotf.setText(pathh);
        }
       // Image img = new Image ("file:"+selectedFile.getAbsolutePath());
       i = new Image("file:"+selectedFile.getAbsolutePath());
        imageView.imageProperty().set(i);
        
        
        });
        
       
         
        HBox ch4 = new HBox(54);
         HBox upload = new HBox(5);
         upload.getChildren().addAll(Logotf,Uploadbtn);
        ch4.setPadding(new Insets(1,1,1,1));
        
        ch4.getChildren().addAll(upload); 
        
        
        Label Typelabel = new Label();
        ComboBox TypeC = new ComboBox(ComboType);
        RemplirComboBox();
        TypeC.setMaxHeight(30);
       
        HBox ch5 = new HBox(5);
        ch5.setPadding(new Insets(2,2,2,2));
        ch5.getChildren().addAll(TypeC); 
        
       
      // TextField Typetf = new TextField();
      //  Typetf.setPromptText("Type");
      /*  HBox ch5 = new HBox(5);
        ch5.setPadding(new Insets(2,2,2,2));
        ch5.getChildren().addAll(Typelabel,Typetf); */
        
        Label Facebooklabel = new Label();
        TextField Facebooktf = new TextField();
        Facebooktf.setPromptText("Facebook");
        HBox ch6 = new HBox(24);
        ch6.setPadding(new Insets(2,2,2,2));
        ch6.getChildren().addAll(Facebooktf); 
        
        Label Numlabel = new Label();
        TextField Numtf = new TextField();
        Numtf.setPromptText("Numero de tel°");
        HBox ch7 = new HBox(7);
        ch7.setPadding(new Insets(2,2,2,2));
        ch7.getChildren().addAll(Numtf); 
        
       Button addbtn = new Button("Ajouter");
         Button annulbtn = new Button("Annuler");
          //#2471A3
         addbtn.setTextFill(Paint.valueOf("#ffffff"));
       //  addbtn.setStyle("#2471A3");
         addbtn.setStyle("fx-background-color:#ffffff");
         
         HBox ch8 = new HBox(7);
        ch8.setPadding(new Insets(2,2,2,2));
        ch8.getChildren().addAll(addbtn,annulbtn); 
        /*
        Nomlabel.setText("Nom espace:");
        Adresselabel.setText("Adresse:");
        Maillabel.setText("E-mail:");
        Logolabel.setText("Logo:");
        Typelabel.setText("Type espace:");
        Facebooklabel.setText("Facebook:");
        Numlabel.setText("Numero de tel°:");
        */
     //  VBox layout = new VBox(10);
        VboxInterfaceAjout.setPadding(new Insets(10,10,10,10));
         
       VboxInterfaceAjout.getChildren().addAll(ch1,ch2,ch3,imageView,ch4,ch5,ch6,ch7,ch8);
       
       annulbtn.setOnAction(e->{
            VboxInterfaceAjout.setVisible(false);
   //     TypeC 
   ComboType.clear();
          VboxInterfaceAjout.getChildren().clear();
           VboxInterfaceSuppModif.setVisible(true);
       });
        
       addbtn.setOnAction(e ->{
           if(nomtf.getText().isEmpty())
           {
               Alert alert = new Alert(Alert.AlertType.WARNING);
              // alert.setTitle("Champs vide");
              alert.setContentText("Remplissez le champ nom");
               alert.setWidth(45);
                       
               alert.showAndWait();
           }
           else if(Adressetf.getText().isEmpty())
           {
               Alert alert = new Alert(Alert.AlertType.WARNING);
              // alert.setTitle("Champs vide");
              alert.setContentText("Remplissez le champ adresse");
               alert.setWidth(45);
                       
               alert.showAndWait();
               
           }
           else if(ValiderNum(Numtf)==false)
           {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              // alert.setTitle("Champs vide");
              alert.setContentText("Numero invalide");
               alert.setWidth(45);
                       
               alert.showAndWait(); 
           }
           else if(ValiderEmail(Mailtf)==false)
           {
                Alert alert = new Alert(Alert.AlertType.WARNING);
              // alert.setTitle("Champs vide");
              alert.setContentText("Adresse mail invalide");
               alert.setWidth(45);
                       
               alert.showAndWait(); 
               
           }
         
           
                   
           else{
           String aString = Numtf.getText(); 
        int x = Integer.parseInt(aString); 
        
        p.setNomEspace(nomtf.getText());
        p.setADRESSE_ES(Adressetf.getText());
        p.setEMAIL_ES(Mailtf.getText());
        p.setFACEBOOK_ES(Facebooktf.getText());
        p.setLOGO_ES( Logotf.getText());
        p.setTYPE_ES((String)TypeC.getSelectionModel().getSelectedItem());///Comtype recupere le champ de combo
        p.setNUMTEL_ES(x);
        
        VboxInterfaceAjout.setVisible(false);
   //     TypeC 
   ComboType.clear();
          VboxInterfaceAjout.getChildren().clear();
           VboxInterfaceSuppModif.setVisible(true);
           CrudEspace crud = new CrudEspace();
       
               try {
                   crud.insertPST(p);
               } catch (SQLException ex) {
                   Logger.getLogger(EspaceController.class.getName()).log(Level.SEVERE, null, ex);
               }
               try {
                   refreshTable();
               } catch (SQLException ex) {
                  
               }
                  }
           //return p;
       });
          
        /*  addbtn.setOnAction(e ->{
          
          });*/
        
      /*  PannelSuppModif.setVisible(false);
        
        Label Adresselabel = new Label();
        TextField Adressetf = new TextField();
        Adressetf.setPromptText("Adresse");
        HBox ch2 = new HBox(34);
        ch2.setPadding(new Insets(2,2,2,2));
        ch2.getChildren().addAll(Adresselabel,Adressetf); 
        
         VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
         Button addbtn = new Button("Ajouter");
          layout.getChildren().addAll(ch2,addbtn);
       // PannelBoutons.setVisible(false);*/
     /*   AjoutInterface AI = new AjoutInterface();
        
       
        CrudEspace crud = new CrudEspace();
       
        crud.insertPST(AI.DisplayInterface());
        refreshTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Espace ajoutée avec succés");
        alert.showAndWait();
        clearFields();*/
        
      /*  try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAddPerson.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
              
    } catch(Exception e) {
       e.printStackTrace();
      }
        String aString = numtel.getText(); 
        int x = Integer.parseInt(aString); 
       
        Espace p = new Espace(nomtf.getText() ,adressetf.getText() , mailtf.getText() , x ,facebooktf.getText() , logotf.getText(),  typetf.getText());
        
        CrudEspace crud = new CrudEspace();
       
        crud.insertPST(p);
        refreshTable();
        clearFields();

     */
        
    }
    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        CrudEspace crud = new CrudEspace();
      //  crud.SearchByType();
         setCelluleValue();
         refreshTableSearch();
      
    
         table.getItems().addAll(crud.SearchByType(searchtf.getText()));
        
        
        
    }
    
     public void RemplirComboBox()//rendre la methode qui recupére de la table type(nouvel table)
    {
        ComboType.add("Salle de sport");
        ComboType.add("Salle de beauté");
        ComboType.add("Spa");
        ComboType.add("Centre de therapie");
        
        
        
    }
    
    public boolean ValiderEmail(TextField tx)
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(tx.getText());
        if(m.find() && m.group().equals(tx.getText()))
        {
            return true;
        }
        return false;
    }
    
    public boolean ValiderNum(TextField tx)
    {
        Pattern p = Pattern.compile("(2|5|7|9)?[0-9]{7}");
        Matcher m = p.matcher(tx.getText());
        if(m.find() && m.group().equals(tx.getText()))
        {
            return true;
        }
        return false;
        
    }
    
    
    public void refreshTable() throws SQLException
    {
        CrudEspace ser = new CrudEspace();
        ser.displayAllObs().clear();
     table.setItems(ser.displayAllObs());
    }
    public void refreshTableSearch() throws SQLException
    {
       CrudEspace ser = new CrudEspace();
       ser.displayAllObs().clear();
       ser.SearchByType(typetf.getText()).clear();
       table.setItems(ser.SearchByType(typetf.getText()));
          
    }
    public void clearFields()
    {
        idtf.clear();
          nomtf.clear();
         adressetf.clear();
         mailtf.clear();
         logotf.clear();
        typetf.clear();
        numtel.clear();
        facebooktf.clear();
        ImLogo.imageProperty().set(null);
        
    }

   public void setCelluleValue()
   {
       nomcl.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNomEspace());
        });
      idEspacecl.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getID_ESPACESTR());
        });
     adressecl.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getADRESSE_ES());
        });
      mailcl.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getEMAIL_ES());
        });
      
      NumCl.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNUMTEL_ESStr());
        });
      facebookcl.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getFACEBOOK_ES());
        });
      logocl.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getLOGO_ES());
        });
      typecl.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getTYPE_ES());
        });
      
       
   }

   



    

    

    

    
    
    
}
