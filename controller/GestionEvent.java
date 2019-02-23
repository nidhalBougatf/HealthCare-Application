package controller;

import Services.CrudEvenement;
import entities.Evenement;
import entities.Utilisateur;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import maps.java.Geocoding;

/**
 * FXML Controller class
 *
 * @author koussai
 */
public class GestionEvent implements Initializable {

    @FXML 
    private ImageView AfficheIMG ;
    @FXML
    private TextField Titre;
    @FXML
    private TextArea Description;
    @FXML
    private TextField FilePath;
    @FXML
    private Button UploadFile;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private TextField Lieu;
    
    @FXML
    private TextField Duree;
    @FXML
    private TextField Frais;
    @FXML
    private TextField Organisateur;
    @FXML
    private TextField Contact;
    @FXML
    private ComboBox<String> Type;
    @FXML
    private Button AddEvent;
    @FXML
    private Button EditEvent;
    @FXML
    private Button EditEvenet;
    @FXML
    private TextField Chercher;
    @FXML
    private Button chercher;
    @FXML
    private CheckBox Rando;
    @FXML
    private CheckBox voyage;
    @FXML
    private CheckBox Camp;
    @FXML
    private CheckBox marathon;
    @FXML
    private CheckBox autre;
    @FXML
    private Button filtre;
    @FXML
    private TableView<Evenement> eventList;
    @FXML
    private TableColumn<Evenement, Integer> EventID;
    @FXML
    private TableColumn<Evenement, Integer> UserID;
    @FXML
    private TableColumn<Evenement, String> TITRE;
    @FXML
    private TableColumn<Evenement, String> DESCRIPTION;
    @FXML
    private TableColumn<Evenement, Date> DATEDEBUT;
    @FXML
    private TableColumn<Evenement, Date> DATEFIN;
    @FXML
    private TableColumn<Evenement, String> DUREE;
    @FXML
    private TableColumn<Evenement, String> FRAIS;
    @FXML
    private TableColumn<Evenement, String> ORGANISATEUR;
    @FXML
    private TableColumn<Evenement, String> CONTACT;
    @FXML
    private TableColumn<Evenement, String> AFFICHE;
    @FXML
    private TableColumn<Evenement, String> TYPE;
    @FXML
    private TableColumn<Evenement, String> LIEU;
    @FXML
    private WebView WebView ; 
    
    

    
     
     
    public int ID_Event = 0 ; 
    CrudEvenement CE = new CrudEvenement() ; 
    Evenement E  = null ; 
    String TitreEvent  = "" ;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle RB) {
       
        System.out.println("Id");
        UpdateList() ; 
         try { eventList.setItems(CE.displayAllEvenement()); } 
         catch (SQLException ex) {System.out.println("Combox : " + ex);  }
         ObservableList Combo =  FXCollections.observableArrayList("Randonné","Voyage","Camping","Marathon","Autre") ; 
         Type.setItems(Combo);       
         
       eventList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                       Evenement SelectedEvenet = eventList.getItems().get(eventList.getSelectionModel().getSelectedIndex());
                       E = SelectedEvenet ;
                        vider();
            }           
        });
         
    }    

    public  void UpdateList() {
                
        EventID.setCellValueFactory(new  PropertyValueFactory<>("ID_EVENEMENT"));
        UserID.setCellValueFactory(new  PropertyValueFactory<>("ID_UTILISATEUR"));
        TITRE.setCellValueFactory(new  PropertyValueFactory<>("TITRE_E"));
        DESCRIPTION.setCellValueFactory(new  PropertyValueFactory<>("DESCRIPTION_E"));
        AFFICHE.setCellValueFactory(new  PropertyValueFactory<>("AFFICHE_E"));
        DATEDEBUT.setCellValueFactory(new  PropertyValueFactory<>("DATEDEBUT_E"));
        DATEFIN.setCellValueFactory(new  PropertyValueFactory<>("DATEFIN_E"));
        FRAIS.setCellValueFactory(new  PropertyValueFactory<>("FRAIS_E"));
        LIEU.setCellValueFactory(new  PropertyValueFactory<>("LIEU_E"));
        DUREE.setCellValueFactory(new  PropertyValueFactory<>("DUREE_E"));
       ORGANISATEUR.setCellValueFactory(new  PropertyValueFactory<>("ORGANISATEUR_E"));
        CONTACT.setCellValueFactory(new  PropertyValueFactory<>("CONTACT_E"));
        TYPE.setCellValueFactory(new  PropertyValueFactory<>("TYPE_E"));
    }

    
    @FXML
    private void UplaodImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            FilePath.setText(file.getAbsolutePath());
            
            Image img = new Image("file:"+file.getAbsolutePath());
            AfficheIMG.imageProperty().set(img); 
    }

    public void transAtt () {
        ID_Event  = E.getID_EVENEMENT() ;
        Titre.setText(E.getTITRE_E());
        Description.setText(E.getDESCRIPTION_E());
        FilePath.setText(E.getAFFICHE_E());
        Image img = new Image("file:"+FilePath.getText());
            AfficheIMG.imageProperty().set(img); 
        DateDebut.valueProperty().setValue(E.getDATEDEBUT_E().toLocalDate());
        DateFin.valueProperty().setValue(E.getDATEFIN_E().toLocalDate());
        Lieu.setText(E.getLIEU_E());
        String n =  String.valueOf(E.getDUREE_E()) ;
        String n2 =  String.valueOf(E.getFRAIS_E()) ;
        Duree.setText(n);
        Frais.setText(n2);
        Organisateur.setText(E.getORGANISATEUR_E());
        Contact.setText(E.getCONTACT_E());
        Type.getSelectionModel().select(E.getTYPE_E()) ;
    }
    
    @FXML
    private void AjouterEvenet(ActionEvent event) throws SQLException {
       
        if ("".equals(Titre.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Le titre est un champ obligatoire !");
            alert.showAndWait();
        } else if ("".equals(Description.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("La description est un champ obligatoire !");
            alert.showAndWait();
        } else if (LocalDate.now().toEpochDay() >= DateDebut.getValue().toEpochDay() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur Date");
            alert.setHeaderText("Attention");
            alert.setContentText("la date d'évenement doit etre superieur à la date courant !");
            alert.showAndWait();
        } else if (LocalDate.now().toEpochDay() >= DateFin.getValue().toEpochDay() || DateFin.getValue().toEpochDay()<DateDebut.getValue().toEpochDay()  ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la date du fin de l'evenement !");
            alert.showAndWait();
        }else if ( "".equals(Duree.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la durée de l'evenemenemt !");
            alert.showAndWait();  
        } else if ( "".equals(Frais.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la durée de l'evenemenemt !");
            alert.showAndWait();  
        }else if ( "".equals(Organisateur.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie le nom de l'organisateur  !");
            alert.showAndWait();  
        }
        else if ( "".equals(Contact.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie les contacts  !");
            alert.showAndWait();  
        }else if ((Type.getSelectionModel().getSelectedIndex())== 0 ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("choisir le type !");
            alert.showAndWait();  
        }
        else {
            try {
                E = new  Evenement( Titre.getText(), Description.getText(), FilePath.getText() , Date.valueOf(DateDebut.getValue().toString()) , Date.valueOf(DateFin.getValue().toString()), Lieu.getText(),Integer.parseInt(Duree.getText()) , Float.parseFloat(Frais.getText()), Organisateur.getText(), Contact.getText(), Type.getValue());
                System.out.println(E);            
                E.setID_UTILISATEUR(LoginGUIController.CurrentUser.getId());
                CE.InsertEvenement(E);
                eventList.setItems(CE.displayAllEvenement());
                vider () ; 
            } catch (SQLException ex) {
                System.out.println(ex);
            }
         }
       
    }

    @FXML
    private void EditEvenet(ActionEvent event) throws SQLException, UnsupportedEncodingException, MalformedURLException {
        E = null ; 
        E = eventList.getItems().get(eventList.getSelectionModel().getSelectedIndex());
        if ("Enregistrer".equals(EditEvent.getText()))
        {if ("".equals(Titre.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Le titre est un champ obligatoire !");
            alert.showAndWait();
        } else if ("".equals(Description.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("La description est un champ obligatoire !");
            alert.showAndWait();
        } else if (LocalDate.now().toEpochDay() >= DateDebut.getValue().toEpochDay() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur Date");
            alert.setHeaderText("Attention");
            alert.setContentText("la date d'évenement doit etre superieur à la date courant !");
            alert.showAndWait();
        } else if (LocalDate.now().toEpochDay() >= DateFin.getValue().toEpochDay() || DateFin.getValue().toEpochDay()<DateDebut.getValue().toEpochDay()  ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la date du fin de l'evenement !");
            alert.showAndWait();
        }else if ( "".equals(Duree.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la durée de l'evenemenemt !");
            alert.showAndWait();  
        } else if ( "".equals(Frais.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie la durée de l'evenemenemt !");
            alert.showAndWait();  
        }else if ( "".equals(Organisateur.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie le nom de  l'organisateur  !");
            alert.showAndWait();  
        }
        else if ( "".equals(Contact.getText()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie les contacts  !");
            alert.showAndWait();  
        }else if ((Type.getSelectionModel().getSelectedIndex())== 0 ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Attention");
            alert.setContentText("Verifie chois  !");
            alert.showAndWait();  
        }
        else  {
        E = new  Evenement( Titre.getText(), Description.getText(), FilePath.getText() , java.sql.Date.valueOf(DateDebut.getValue()) , java.sql.Date.valueOf(DateFin.getValue()), Lieu.getText(),Integer.parseInt(Duree.getText()) , Float.parseFloat(Frais.getText()), Organisateur.getText(), Contact.getText(), Type.getValue());         
        System.out.println("Titre chap : "+Titre.getText());
        System.out.println("Classe chap : "+Titre.getText());
        E.setID_UTILISATEUR(LoginGUIController.CurrentUser.getId());
        E.setID_EVENEMENT(ID_Event);
        System.out.println(ID_Event);
        CE.UpdateEvenement(E,E.getID_EVENEMENT());
        eventList.setItems(CE.displayAllEvenement());
        vider () ;   
        EditEvent.setText("Modifier");}
        }
        
        if ("Modifier".equals(EditEvent.getText())){
        transAtt () ;
        AddEvent.setVisible(false);
        EditEvent.setText("Enregistrer");
        }
        
    }

    
    @FXML
    private void DeletEvent(ActionEvent event) throws SQLException {
        E = eventList.getItems().get(eventList.getSelectionModel().getSelectedIndex());
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation la suppression");
        alert.setHeaderText("Confirmer");
        alert.setContentText("Vous-etes sur de supprimer l'evenement "+ E.getTITRE_E() +" ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
             CE.DeleteEvenement(E.getID_EVENEMENT());
        }
         
        try {        
           eventList.setItems(CE.displayAllEvenement());
            vider () ; 
        } catch (SQLException ex) { }
       
    }

    @FXML
    private void Search(ActionEvent event) {
         try {        
           eventList.setItems(CE.Search(Chercher.getText()));
        } catch (SQLException ex) {
             System.out.println(ex);
        } 
    }

    @FXML
    private void Filtre(ActionEvent event) {
        String Type = "''" ; 
        if(Rando.isSelected()) { Type+=" or TYPE_E ='Randonné'" ;}
        if(voyage.isSelected()) { Type+="or TYPE_E ='Voyage'";}
        if(Camp.isSelected()) { Type+=" or TYPE_E ='Camping'";}
        if(marathon.isSelected()) { Type+=" or TYPE_E = 'Marathon'";}
        if(autre.isSelected()) { Type+=" or TYPE_E = 'Autre'";}
     
        
        try {
           eventList.setItems(CE.displayFiltreEvenement(Type));
        } catch (SQLException ex) { System.out.println(ex);
        } 
        

    }
    
    
    
    public void vider () {
    E = null ; 
    Titre.setText(""); 
    Description.setText("");  
    FilePath.setText("");
    DateDebut.setValue(null);  
    DateFin.setValue(null);  
    Lieu.setText("");
    Duree.setText("");
    Frais.setText("");
    Organisateur.setText("");
    Contact.setText("");
    Type.setValue("");
    Type.setPromptText("Type");
    AfficheIMG.imageProperty().set(null);
    EditEvent.setText("Modifier");
    AddEvent.setVisible(true);
    }

    @FXML
    private void SlectTable(MouseEvent event) {
    }

    @FXML
    private void Miseajour(ActionEvent event) throws SQLException {
        vider();
        eventList.setItems(CE.displayAllEvenement());
        E = null ; 
    }

     

    private void LieuChanged(ActionEvent event) {
       
    }

    @FXML
    private void LieuChanged2(ActionEvent event) {
         try {
         Geocoding ObjGeocoding=new maps.java.Geocoding();
          String Alt = null , Long = null  ;      
          Point2D.Double resultado=ObjGeocoding.getCoordinates(Lieu.getText());
          Alt =  (String.valueOf(resultado.x));
          Long = (String.valueOf(resultado.y));
          WebEngine engine = WebView.getEngine();
          String url = "https://www.google.tn/maps/@"+Alt+","+Long+",15z?hl=fr";
          engine.load(url);
           System.out.println(url);
           } catch (Exception e) {
               System.out.println("controller.GestionEvent.LieuChanged()"+e);
        }
    }
}

