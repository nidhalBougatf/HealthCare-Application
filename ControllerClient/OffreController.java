package ControllerClient;



import Services.CrudEvaluation;

import Services.CrudOffre;
import Services.CrudUtilisateur;
import controller.LoginGUIController;
//import Services.CrudNote;
import entities.Evaluation;

import entities.Offre;
import entities.Utilisateur;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
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


/**
 * FXML Controller class
 *
 * @author Nidhal
 */
public class OffreController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private ScrollPane listoffre;
    @FXML
    private TextField titreoffrerech;
    @FXML
    private ComboBox<String> nomespacerech;
    @FXML
    private ComboBox<String> disponibilite;
    @FXML
    private Slider prixminrech;
    @FXML
    private Slider prixmaxrech;
    @FXML
    private Button btnappliquer;
    @FXML
    private Button reinitialiser;
    
    private  ObservableList<String> myespaces=FXCollections
            .observableArrayList() ;
    private ObservableList<String> mydispo=FXCollections
            .observableArrayList("Tous","Disponible","Epuise") ;

        
       CrudOffre co= new CrudOffre() ; 
       Offre o = null ; 
     CrudEvaluation CEval = new CrudEvaluation() ;
     
    public OffreController() {
        this.co = new CrudOffre();
    }
    
    public  Node ListofAllOffre () throws SQLException {

        VBox root0 = new VBox(10) ; //spacing 10

        ObservableList<Offre> OB = FXCollections.observableArrayList() ; 
        OB = co.displayAllOffre();
        
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
        root.setPadding(new Insets(5,5, 5,5));
           Offre o = OB.get(i)   ;
        try {

        //FileInputStream input = new FileInputStream("C:\\image\\logo.png");
        Image image = new Image("file:"+o.getAFFICHE_O());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        
        
        //Offre
        Label Titre = new Label(o.getTITRE_O()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        Label Des = new Label(o.getDESCRIPTION_O()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
        Label Datefin = new Label("Date fin : " +o.getDATEFIN_O()) ; 
        Datefin.setTextFill(Color.web("#17202A"));
        Label prix = new Label("Prix : " +o.getNOUVEAU_PRIX()) ;
        prix.setTextFill(Color.web("#17202A"));
        Label nomesp = new Label("Nom espace :"+o.getNom_es()) ;
        nomesp.setTextFill(Color.web("#17202A"));

        // details
             HBox Hbtn = new HBox(10);
           Button button = new Button("Plus de détails") ;
           button.setStyle("-fx-background-color:  #2471A3"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+o.getID_OFFRE());
           button.onActionProperty().set((event) -> {
               System.out.println(button.getAccessibleText());
            try { 
                listoffre.setContent(AfficheOffre(button.getAccessibleText()));
                
            } catch (SQLException ex) 
                {
                System.out.println("ex1 "+ex);  
                } catch (FileNotFoundException ex) {
                System.out.println("ex2 "+ex); 
                }
           });
           
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#17202A"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Offre", o.getID_OFFRE())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(Datefin,prix,nomesp) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println("ex11 "+e); 
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }
    
    
     @FXML
    public void chercher(KeyEvent event) throws  SQLException 
    {
        if ("".equals(titreoffrerech.getText())) 
        {
            try { 
                listoffre.setContent(ListofAllOffre());
            } catch (Exception ex) 
                {
                System.out.println("ex1 "+ex);  
                } 
           
        }
        else
        {
            String titr = titreoffrerech.getText() ;
            try { 
                listoffre.setContent(ListSearchOffreTitre(titr));
            } catch (Exception ex) 
                {
                System.out.println("ex1 "+ex);  
                } 
    
        }
    }
    
    
    
    public  Node ListSearchOffreTitre (String titre) throws SQLException 
    {
     VBox root0 = new VBox(10) ; //spacing 10

        ObservableList<Offre> OB = FXCollections.observableArrayList() ; 
        OB = co.searchOffreByTitre(titre);
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
        root.setPadding(new Insets(5,5, 5,5));
           Offre o = OB.get(i)   ;
        try {

        //FileInputStream input = new FileInputStream("C:\\image\\logo.png");
        Image image = new Image("file:"+o.getAFFICHE_O());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        
        
        //Offre
        Label Titre = new Label(o.getTITRE_O()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        Label Des = new Label(o.getDESCRIPTION_O()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
        Label Datefin = new Label("Date fin : " +o.getDATEFIN_O()) ; 
        Datefin.setTextFill(Color.web("#17202A"));
        Label prix = new Label("Prix : " +o.getNOUVEAU_PRIX()) ;
        prix.setTextFill(Color.web("#17202A"));
        Label nomesp = new Label("Nom espace :"+o.getNom_es()) ;
        nomesp.setTextFill(Color.web("#17202A"));

        // details
             HBox Hbtn = new HBox(10);
           Button button = new Button("Plus de détails") ;
           button.setStyle("-fx-background-color:  #2471A3"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+o.getID_OFFRE());
           button.onActionProperty().set((event) -> {
               System.out.println(button.getAccessibleText());
            try { 
                listoffre.setContent(AfficheOffre(button.getAccessibleText()));
                
            } catch (SQLException ex) 
                {
                System.out.println("ex1 "+ex);  
                } catch (FileNotFoundException ex) {
                System.out.println("ex2 "+ex); 
                }
           });
           
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#17202A"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Offre", o.getID_OFFRE())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(Datefin,prix,nomesp) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println("ex11 "+e); 
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
        
    }
    
    
    public Node AfficheOffre (String id) throws SQLException, FileNotFoundException {
        
      o =  co.getOffreById(Integer.parseInt(id)) ; 
        
      VBox V0 = new VBox(10) ;
      HBox H0 = new HBox(6) ; 
      VBox V2 = new VBox(10) ; 
      
        Image image = new Image("file:"+o.getAFFICHE_O());
        ImageView IMG = new ImageView(image);
        IMG.preserveRatioProperty().set(true);
        
         HBox Hnote = new HBox(10) ; 
         Label Titre1 = new Label() ; 
         Titre1.setFont(new Font("Arial", 16));
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
         SubmitNote.accessibleTextProperty().set(""+o.getID_OFFRE());
        
        VBox V1 = new VBox(10) ;
        
        Button btnExit = new Button("< retour ") ;
        btnExit.setStyle("-fx-background-color:  #2471A3 ; -fx-font-weight: bold" );
        btnExit.setTextFill(Color.web("#FDFEFE"));
        btnExit.onActionProperty().set((event) -> {
          try {
              listoffre.setContent(ListofAllOffre());
          } catch (SQLException ex) {
                System.out.println("ex5 "+ex);           
          }
        }) ; 
        
        
        //Offre
        HBox H1 = new HBox(8) ; 
        Label Titre  = new Label(o.getTITRE_O()) ; 
        Titre.setFont(new Font("Arial", 30));
        TextArea Description = new TextArea(o.getDESCRIPTION_O()) ; 
        Description.setWrapText(true);
        Description.setFont(new Font("Arial", 14));
        Description.editableProperty().set(false);

        HBox H2 = new HBox(10) ; 
        Label Datedebut = new Label("Date début : " +o.getDATEDEBUT_O()) ; 
        Datedebut.setFont(new Font("Arial", 14));
        Label Datefin = new Label("Date fin : " +o.getDATEFIN_O()) ; 
        Datefin.setFont(new Font("Arial", 14));
        Label ancprix = new Label("Ancien Prix : " +o.getANCIEN_PRIX()) ;
        ancprix.setFont(new Font("Arial", 14));
        Label nvprix = new Label("Nouveau Prix : " +o.getNOUVEAU_PRIX()) ;
        nvprix.setFont(new Font("Arial", 14));
        Label nomesp = new Label("Nom espace : "+co.getNomEspaceID(o.getID_ESPACE())) ;
        nomesp.setFont(new Font("Arial", 14));
        

        H1.getChildren().addAll(btnExit,Titre) ;
        H1.setAlignment(Pos.CENTER_LEFT);  
        H2.getChildren().addAll(Datedebut,Datefin) ;
        V1.getChildren().addAll(H1,Description,H2,ancprix,nvprix,nomesp) ; 
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
        ObservableList<Evaluation> EvalU = CEval.commentAndNoteByuser("Offre" ,o.getID_OFFRE()) ; 
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
             Evaluation Eval = new Evaluation(0, LoginGUIController.CurrentUser.getId(), Integer.parseInt(NoteValue.getText()) , Comment.getText() ,"Offre" , null , o.getID_OFFRE(), Date.valueOf(LocalDate.now())) ;
             CrudEvaluation IsertNote = new CrudEvaluation(); 
             
          try {
              IsertNote.insertEvaluation(Eval);
              System.out.println("Evamuation Donne  ");
              
                 try {
                     listoffre.setContent(AfficheOffre(String.valueOf(o.getID_OFFRE())));
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
   
     
    @FXML
    public void appliquer(ActionEvent event) throws IOException, SQLException 
    { 
        
        Double ancprix=prixminrech.getValue();
        Double nvprix=prixmaxrech.getValue();
        String nomespa = nomespacerech.getValue().toString() ;
        String dispoesp = disponibilite.getValue().toString() ;
        
        if(ancprix !=0 || nvprix !=0)
        {
           if(nvprix<=ancprix)
            {
                 Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Le prix minimal doit etre inferieur au prix maximal ! ");
                a.showAndWait();  
            }
            else
            {        
                 try { 
                listoffre.setContent(ListSearchOffreMultiple(nomespa,dispoesp,ancprix,nvprix));
                } catch (Exception ex) 
                {
                System.out.println("ex1 "+ex);  
                } 
                      
            } 
        }
        else
        {
            try { 
                listoffre.setContent(ListSearchOffreMultiple(nomespa,dispoesp,0.0,0.0));
                } catch (Exception ex) 
                {
                System.out.println("ex1 "+ex);  
                } 
                        
        }
   
    }

    public  Node ListSearchOffreMultiple(String nomespa,String dispoesp,Double ancprix,Double nvprix) throws SQLException 
    {
     VBox root0 = new VBox(10) ; //spacing 10

        ObservableList<Offre> OB = FXCollections.observableArrayList() ; 
        if(ancprix !=0 || nvprix !=0)
        co.searchOffreMultiple(nomespa,dispoesp,ancprix,nvprix).forEach(e -> { OB.add(e);});
        else
        co.searchOffreWithoutprix(nomespa,dispoesp).forEach(e -> { OB.add(e);});    
        
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
        root.setPadding(new Insets(5,5, 5,5));
           Offre o = OB.get(i)   ;
        try {

        //FileInputStream input = new FileInputStream("C:\\image\\logo.png");
        Image image = new Image("file:"+o.getAFFICHE_O());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        
        
        //Offre
        Label Titre = new Label(o.getTITRE_O()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        Label Des = new Label(o.getDESCRIPTION_O()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
        Label Datefin = new Label("Date fin : " +o.getDATEFIN_O()) ; 
        Datefin.setTextFill(Color.web("#17202A"));
        Label prix = new Label("Prix : " +o.getNOUVEAU_PRIX()) ;
        prix.setTextFill(Color.web("#17202A"));
        Label nomesp = new Label("Nom espace :"+o.getNom_es()) ;
        nomesp.setTextFill(Color.web("#17202A"));

        // details
             HBox Hbtn = new HBox(10);
           Button button = new Button("Plus de détails") ;
           button.setStyle("-fx-background-color:  #2471A3"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+o.getID_OFFRE());
           button.onActionProperty().set((event) -> {
               System.out.println(button.getAccessibleText());
            try { 
                listoffre.setContent(AfficheOffre(button.getAccessibleText()));
                
            } catch (SQLException ex) 
                {
                System.out.println("ex1 "+ex);  
                } catch (FileNotFoundException ex) {
                System.out.println("ex2 "+ex); 
                }
           });
           
        //Note
         Separator sep3 =new Separator(Orientation.HORIZONTAL) ;
        HBox HNote = new HBox(6);
        
        HNote.setAlignment(Pos.CENTER_RIGHT); 
        Label Note = new  Label ("Note : ");
        Note.setTextFill(Color.web("#17202A"));
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Offre", o.getID_OFFRE())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(Datefin,prix,nomesp) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception e) {
            System.out.println("ex11 "+e); 
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }
    
    
    
     public void buildNomEspace()
    {
         CrudOffre co = new CrudOffre();
        try {
            
             myespaces =FXCollections.observableArrayList(co.getAllEspace());
             
            
        } catch (SQLException ex) {
            System.out.println("ex10 "+ex);  
        }
    }
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        prixminrech.setMin(0);
            prixminrech.setMax(500);
            prixminrech.setValue(0);
            prixminrech.setShowTickLabels(true);
            prixminrech.setShowTickMarks(true);
            prixminrech.setMajorTickUnit(50);
            prixminrech.setMinorTickCount(5);
            prixminrech.setBlockIncrement(5);
            
            prixmaxrech.setMin(0);
            prixmaxrech.setMax(500);
            prixmaxrech.setValue(0);
            prixmaxrech.setShowTickLabels(true);
            prixmaxrech.setShowTickMarks(true);
            prixmaxrech.setMajorTickUnit(50);
            prixmaxrech.setMinorTickCount(5);
            prixmaxrech.setBlockIncrement(5);

            
            buildNomEspace();

            nomespacerech.setValue("Tous");
            nomespacerech.setItems(myespaces);
            
            disponibilite.setValue("Tous");
            disponibilite.setItems(mydispo);
        
        
        try {
            listoffre.setContent(ListofAllOffre());
            
        } catch (SQLException ex) {
            System.out.println("ex9 "+ex);         }
        
    }    

   
   
    @FXML
    private void reinitialiser(ActionEvent event) throws SQLException {
       try { 
                listoffre.setContent(ListofAllOffre());
                } catch (Exception ex) 
                {
                System.out.println("ex1 "+ex);  
                } 
    }
    
}
