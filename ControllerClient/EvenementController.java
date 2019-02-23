package ControllerClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Services.CrudEvaluation;
import Services.CrudEvenement;
import Services.CrudUtilisateur;
import controller.LoginGUIController;
import entities.Evaluation;
import entities.Evenement;
import entities.Utilisateur;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import maps.java.Geocoding;

/**
 * FXML Controller class
 *
 * @author koussai
 */
public class EvenementController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private ScrollPane LisTEvent;
      CrudEvenement CE = new CrudEvenement() ; 
       Evenement E = null ; 
       CrudEvaluation CEval = new CrudEvaluation() ; 
    @FXML
    private CheckBox Rando;
    @FXML
    private CheckBox marathon;
    @FXML
    private CheckBox Camp;
    @FXML
    private CheckBox Voyage;
    @FXML
    private CheckBox autre;
    
   

 

    public  Node ListofAllEvent () throws SQLException {
        VBox root0 =new VBox(10) ; 
        ObservableList<Evenement> OB = FXCollections.observableArrayList() ; 
        OB = CE.displayAllEvenement() ; 
        
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
       root.setPadding(new Insets(5,5, 5,5));

        try {
       Evenement E = OB.get(i)   ;
     //   FileInputStream input = new FileInputStream("C:\\Users\\koussai\\Downloads\\19956753_117137918905954_6849634775297747404_o (1).png");
        Image image = new Image("file:"+E.getAFFICHE_E());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        
        
        //Titre
        Label Titre = new Label(E.getTITRE_E()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        //Description 
        Label Des = new Label(E.getDESCRIPTION_E()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
       
        //Details 
        Label DateDebut = new Label("Date début : " +E.getDATEDEBUT_E()) ; 
        DateDebut.setTextFill(Color.web("#17202A"));
        Label Lieu = new Label("Lieu : "+E.getLIEU_E()) ;
        Lieu.setTextFill(Color.web("#17202A"));
        Label Frais = new Label("Frais : " +E.getFRAIS_E()) ;
        Frais.setTextFill(Color.web("#17202A"));
        Label orga = new Label("Organisateur :"+E.getORGANISATEUR_E()) ;
        orga.setTextFill(Color.web("#17202A"));
        Label Type = new Label("Type :"+E.getTYPE_E()) ;
        Type.setTextFill(Color.web("#17202A"));
        //Voir Plus
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #2471A3"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+E.getID_EVENEMENT());
           button.onActionProperty().set((event) -> {
            try { 
                LisTEvent.setContent(AfficheEvent (button.getAccessibleText()));
            } catch (SQLException ex) {
               
            } catch (FileNotFoundException ex) {
                
            }
           });
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#17202A"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Evenement", E.getID_EVENEMENT())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(DateDebut,Lieu,Frais,orga,Type) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println(e);
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }
    public  Node ListofSearchEvent () throws SQLException {
       VBox root0 =new VBox(10) ; 
        ObservableList<Evenement> OB = FXCollections.observableArrayList() ; 
        OB = CE.Search(Titre.getText()); 
        
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
       root.setPadding(new Insets(5,5, 5,5));

        try {
            
       Evenement E = OB.get(i)   ;
     //   FileInputStream input = new FileInputStream("C:\\Users\\koussai\\Downloads\\19956753_117137918905954_6849634775297747404_o (1).png");
        Image image = new Image("file:"+E.getAFFICHE_E());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
   
        
        //Titre
        Label Titre = new Label(E.getTITRE_E()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        //Description 
        Label Des = new Label(E.getDESCRIPTION_E()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
       
        //Details 
        Label DateDebut = new Label("Date début : " +E.getDATEDEBUT_E()) ; 
        DateDebut.setTextFill(Color.web("#17202A"));
        Label Lieu = new Label("Lieu : "+E.getLIEU_E()) ;
        Lieu.setTextFill(Color.web("#17202A"));
        Label Frais = new Label("Frais : " +E.getFRAIS_E()) ;
        Frais.setTextFill(Color.web("#17202A"));
        Label orga = new Label("Organisateur :"+E.getORGANISATEUR_E()) ;
        orga.setTextFill(Color.web("#17202A"));
        Label Type = new Label("Type :"+E.getTYPE_E()) ;
        Type.setTextFill(Color.web("#17202A"));
        //Voir Plus
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #2471A3"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+E.getID_EVENEMENT());
           button.onActionProperty().set((event) -> {
            try { 
                LisTEvent.setContent(AfficheEvent (button.getAccessibleText()));
            } catch (SQLException ex) {
               
            } catch (FileNotFoundException ex) {
                
            }
           });
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#17202A"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Evenement", E.getID_EVENEMENT())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(DateDebut,Lieu,Frais,orga,Type) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println(e);
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }
    public  Node ListofFiltreEvent (String type) throws SQLException {
       VBox root0 =new VBox(10) ; 
        ObservableList<Evenement> OB = FXCollections.observableArrayList() ; 
        OB = CE.displayFiltreEvenement(type); 
        
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
       root.setPadding(new Insets(5,5, 5,5));

        try {
       
        FileInputStream input = new FileInputStream("C:\\Users\\koussai\\Downloads\\19956753_117137918905954_6849634775297747404_o (1).png");
        Image image = new Image(input);
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        Evenement E = OB.get(i)   ;
        
        //Titre
        Label Titre = new Label(E.getTITRE_E()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        //Description 
        Label Des = new Label(E.getDESCRIPTION_E()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
       
        //Details 
        Label DateDebut = new Label("Date début : " +E.getDATEDEBUT_E()) ; 
        DateDebut.setTextFill(Color.web("#17202A"));
        Label Lieu = new Label("Lieu : "+E.getLIEU_E()) ;
        Lieu.setTextFill(Color.web("#17202A"));
        Label Frais = new Label("Frais : " +E.getFRAIS_E()) ;
        Frais.setTextFill(Color.web("#17202A"));
        Label orga = new Label("Organisateur :"+E.getORGANISATEUR_E()) ;
        orga.setTextFill(Color.web("#17202A"));
        Label Type = new Label("Type :"+E.getTYPE_E()) ;
        Type.setTextFill(Color.web("#17202A"));
        //Voir Plus
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #2471A3"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+E.getID_EVENEMENT());
           button.onActionProperty().set((event) -> {
            try { 
                LisTEvent.setContent(AfficheEvent (button.getAccessibleText()));
            } catch (SQLException ex) {
               
            } catch (FileNotFoundException ex) {
                
            }
           });
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#17202A"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Evenement", E.getID_EVENEMENT())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(DateDebut,Lieu,Frais,orga,Type) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println(e);
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }
    public Node AfficheEvent (String id) throws SQLException, FileNotFoundException {
      E =  CE.EvenementByID(Integer.parseInt(id)) ;
       
      VBox V0 = new VBox(10) ;
      HBox H0 = new HBox(6) ; 
      VBox V2 = new VBox(10) ;  
       
     //   FileInputStream input = new FileInputStream("C:\\Users\\koussai\\Downloads\\19956753_117137918905954_6849634775297747404_o (1).png");
        Image image = new Image("file:"+E.getAFFICHE_E());
        ImageView IMG = new ImageView(image);
        IMG.preserveRatioProperty().set(true);
         HBox Hnote = new HBox(10) ; 
         Titre.setFont(new Font("Arial", 16));
         Label NoteL = new Label("Note") ;  
        
         Label NoteValue = new Label( )  ; 
         RadioButton N1 = new RadioButton("");
         RadioButton N2 = new RadioButton("");
         RadioButton N3 = new RadioButton("");
         RadioButton N4 = new RadioButton("");
         RadioButton N5 = new RadioButton("");
        
         N1.onActionProperty().set((event) -> {
            NoteValue.setText("1");
             N2.selectedProperty().set(false);
             N3.selectedProperty().set(false);
             N4.selectedProperty().set(false);
             N5.selectedProperty().set(false);
         });
         
                 
         N2.onActionProperty().set((event) -> {
              NoteValue.setText("2");
             N1.selectedProperty().set(false);
             N3.selectedProperty().set(false);
             N4.selectedProperty().set(false);
             N5.selectedProperty().set(false);
         });
         
                 
         N3.onActionProperty().set((event) -> {
              NoteValue.setText("3");
             N2.selectedProperty().set(false);
             N1.selectedProperty().set(false);
             N4.selectedProperty().set(false);
             N5.selectedProperty().set(false);
         });
                 
         N4.onActionProperty().set((event) -> {
              NoteValue.setText("4");
             N2.selectedProperty().set(false);
             N3.selectedProperty().set(false);
             N1.selectedProperty().set(false);
             N5.selectedProperty().set(false);
         });
         
                 
         N5.onActionProperty().set((event) -> {
              NoteValue.setText("5");
             N2.selectedProperty().set(false);
             N3.selectedProperty().set(false);
             N4.selectedProperty().set(false);
             N1.selectedProperty().set(false);
         });
         
         Button SubmitNote = new  Button("Noter") ; 
         SubmitNote.accessibleTextProperty().set(""+E.getID_EVENEMENT());
        
         
         
         
        VBox V1 = new VBox(10) ;
        
        Button btnExit = new Button("< Retour ") ; 
        btnExit.setStyle("-fx-background-color:  #2471A3 ; -fx-font-weight: bold" );
        btnExit.setTextFill(Color.web("#FDFEFE"));
       
        btnExit.onActionProperty().set((event) -> {
          try {
              LisTEvent.setContent(ListofAllEvent());
          } catch (SQLException ex) {
              
          }
        }) ; 
        //Titre
        HBox H1 = new HBox(8) ; 
        Label Titre  = new Label(E.getTITRE_E()) ; 
        Titre.setFont(new Font("Arial", 30));
        TextArea Descrip = new TextArea(E.getDESCRIPTION_E()) ; 
        Descrip.setWrapText(true);
        //Description
        Descrip.setFont(new Font("Arial", 14));
        Descrip.editableProperty().set(false);
        Descrip.setMaxHeight(150);
        //Detaille
        HBox H2 = new HBox(10) ; 
        Label DateDebut = new Label("Date de début : " +E.getDATEDEBUT_E()) ; 
        DateDebut.setFont(new Font("Arial", 14));
        Label DateFin = new Label("Date du fib : " +E.getDATEFIN_E()) ; 
        DateFin.setFont(new Font("Arial", 14));
        Label Duree = new  Label("Durée : " +E.getDUREE_E()+" H") ; 
        Duree.setFont(new Font("Arial", 14));
        Label LieuDet = new Label("Lieu : "+E.getLIEU_E()) ;
        LieuDet.setFont(new Font("Arial", 14));
        
        //Localisation
        WebView Loc = new WebView() ; 
        Geocoding ObjGeocoding=new maps.java.Geocoding();
        String Alt = null , Long = null  ;      
        Point2D.Double resultado;
        try {
            resultado = ObjGeocoding.getCoordinates(E.getLIEU_E());
            if (resultado == null){
            Alt = "0.0"; 
            Long="0.0" ; 
            }else
            {
            Alt =  (String.valueOf(resultado.x));
            Long = (String.valueOf(resultado.y));
            WebEngine engine = Loc.getEngine();
            Loc.setMaxSize(700, 200);
            String url = "https://www.google.tn/maps/?q="+Alt+","+Long+"";
            engine.load(url);
            System.out.println(url); 
            }
                 
            
        } catch (UnsupportedEncodingException ex) {
            
        } catch (MalformedURLException ex) {
            
        } 
        
        Label Frais = new Label("Frais : " +E.getFRAIS_E()+ " DT" ) ;
        Frais.setFont(new Font("Arial", 14));
        Label orga = new Label("Organisateur : "+E.getORGANISATEUR_E()) ;
        orga.setFont(new Font("Arial", 14));
        Label Type = new Label("Type : "+E.getTYPE_E()) ;
        Type.setFont(new Font("Arial", 14));
        Label Contact = new Label("Contact : "+E.getCONTACT_E()) ;
        Contact.setFont(new Font("Arial", 14));
        H1.getChildren().addAll(btnExit,Titre) ;
        H1.setAlignment(Pos.CENTER_LEFT);  
        H2.getChildren().addAll(DateDebut,DateFin,Duree) ;
        V1.getChildren().addAll(H1,Descrip,H2,LieuDet,Frais,orga,Type,Contact,Loc) ; 
        IMG.fitHeightProperty().set(300);
        IMG.fitWidthProperty().set(450);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        Hnote.getChildren().addAll(NoteL,N1,N2,N3,N4,N5,NoteValue,SubmitNote) ;
        TextArea Comment = new TextArea() ; 
        Comment.setPromptText("Commenter");
        Comment.setMaxWidth(300);
        Comment.setMaxHeight(100);
        ScrollPane comm = new ScrollPane() ; 
        ObservableList<Evaluation> EvalU = CEval.commentAndNoteByuser("Evenement" ,E.getID_EVENEMENT()) ; 
        VBox ListComm = new VBox(7) ; 
        ListComm.fillWidthProperty().set(true);
        for (int i=0;i<EvalU.size();i++)  {
        HBox Hcomm = new HBox(6) ; 
        
        VBox Vcomm = new  VBox() ; 
        
       
       if (EvalU.get(i).getID_UTILISATEUR()== LoginGUIController.CurrentUser.getId()){
       Hnote.setVisible(false);
       Comment.setVisible(false);
       }
       Utilisateur U = null ;
       CrudUtilisateur CU = new CrudUtilisateur(); 
       U = CU.FindUserById(EvalU.get(i).getID_UTILISATEUR()) ; 
       ImageView Imgcomm ; 
       
            System.out.println(U.getAvatar() );
       if (U.getAvatar() == "" ){
            Imgcomm = new ImageView("/View/images/Avatar.png") ;
            Imgcomm.preserveRatioProperty().set(true);
       }else{
            Imgcomm = new ImageView("file:"+U.getAvatar()) ;
            Imgcomm.preserveRatioProperty().set(true);
        }
       Imgcomm.setFitHeight(50);
       Imgcomm.setFitWidth(50);
        Label UserNameCom = new Label(""+U.getNom()+" "+U.getPrenom()) ; 
        UserNameCom.setFont(new Font("Arial", 16));
        UserNameCom.setTextFill(Color.web("#FDFEFE"));
        Label UserCom = new Label(""+EvalU.get(i).getCOMMENTAIRE_EV()) ;
     //   Lieu.setTextFill(Color.web("#FDFEFE"));
        UserCom.setFont(new Font("Arial", 14));
        Label UserNote = new Label("Note : "+EvalU.get(i).getNOTES_EV()) ;
        UserNote.setTextFill(Color.web("#FDFEFE"));
        UserCom.setFont(new Font("Arial", 14));
        UserCom.setTextFill(Color.web("#FDFEFE"));
        Vcomm.getChildren().addAll(UserNameCom,UserCom,UserNote) ;
        Hcomm.getChildren().addAll(Imgcomm,Vcomm) ;
        Hcomm.setStyle("-fx-background-color:  #2471A3");
           
        ListComm.getChildren().addAll(Hcomm) ;
        
        } 
        
        comm.setContent(ListComm);
        comm.fitToWidthProperty().set(true);
        V2.getChildren().addAll(IMG,Hnote,Comment,comm);
        H0.getChildren().addAll(V1,sep,V2) ;
        V0.getChildren().addAll(H0) ;
        
        
         SubmitNote.onActionProperty().set((event) -> {
     if  (N1.isSelected()||N2.isSelected()||N3.isSelected()||N4.isSelected()||N5.isSelected()){
             System.out.println(SubmitNote.getAccessibleText());
             Evaluation Eval = new Evaluation(0, LoginGUIController.CurrentUser.getId(), Integer.parseInt(NoteValue.getText()) , Comment.getText() ,"Evenement" , E.getTYPE_E() , E.getID_EVENEMENT(), Date.valueOf(LocalDate.now())) ;
             CrudEvaluation IsertNote = new CrudEvaluation(); 
             
          try {
              IsertNote.insertEvaluation(Eval);
              System.out.println("Evamuation Donne  ");
              
                 try {
                     LisTEvent.setContent(AfficheEvent(String.valueOf(E.getID_EVENEMENT())));
                 } catch (FileNotFoundException ex) {
                     
                 }
          } catch (SQLException ex) {
              System.out.println(ex);
          }
         }
     else {
     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Note");
            alert.setContentText("La note doit etre superieur à 0 ");
            alert.showAndWait(); 
     }});
        
        
       return V0 ;
    } 
     
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LisTEvent.setContent(ListofAllEvent());
            
        } catch (SQLException ex) {
            
        }
    }    

    @FXML
    private void ChecherParNom(ActionEvent event) {
        
    }

    @FXML
    private void Filtrer(ActionEvent event) {
                String Type = "''" ; 
       if(Rando.isSelected()) { Type+=" or TYPE_E ='Randonné'" ;}
        if(Voyage.isSelected()) { Type+="or TYPE_E ='Voyage'";}
        if(Camp.isSelected()) { Type+=" or TYPE_E ='Camping'";}
        if(marathon.isSelected()) { Type+=" or TYPE_E = 'Marathon'";}
        if(autre.isSelected()) { Type+=" or TYPE_E = 'Autre'";}
     
        
        
        try {
            LisTEvent.setContent(ListofFiltreEvent(Type));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    
    }

    @FXML
    private void FiltrerParDate(ActionEvent event) {
    }

 

    @FXML
    private void SearchByNom(KeyEvent event) {
                 try {
            LisTEvent.setContent(ListofSearchEvent());
        } catch (SQLException ex) {
            
        }
    }
    
}
