/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerClient;

import Services.CrudArticle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import javafx.scene.control.Alert;
import org.json.JSONException;
import org.json.JSONObject;
import Services.CrudEvaluation;
import controller.LoginGUIController;
import static controller.LoginGUIController.CurrentUser;
import entities.Article;
import entities.Evaluation;
import entities.Utilisateur;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;



public class ArticleController implements Initializable {

    
     CrudArticle CA = new CrudArticle() ; 
       Article A = null ; 
       CrudEvaluation CEval = new CrudEvaluation() ; 
    Alert alert = new Alert(Alert.AlertType.ERROR);  
    @FXML
    private Button ChangerdateH;
    @FXML
    private Label enterphrase;
    @FXML
    private TextField id_year;
    @FXML
    private Label slache1;
    @FXML
    private TextField id_month;
    @FXML
    private Label slache;
    @FXML
    private TextField id_day;
    @FXML
    private Button btnConfirmation;
    @FXML
    private TextField id_signe;
     @FXML
    private TextField id_daily;
    @FXML
    private TextArea id_texthoroscope;
    @FXML
    private TextField chercher;
    @FXML
    private ScrollPane ListArticle;
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
    @FXML
    private Button AnnulerConfirmation;    
    @FXML
    private WebView WebViewLeft;
    @FXML
    private WebView WebViewright;
    public  Node ListofAllArticle () throws SQLException {

        VBox root0 =new VBox(10) ; 

        ObservableList<Article> OB = FXCollections.observableArrayList() ; 
        OB = CA.displayAllArticle(); 
        
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3"); 
        root.setPadding(new Insets(5,5, 5,5));
        
       

        try {
        Article A = OB.get(i)   ;
        Image image = new Image("file:"+A.getAFFICHE_A());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
       
        
        //Titre
        Label Titre = new Label(A.getTITRE_A()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        //Description 
        Label Des = new Label(A.getDESCRIPTION_A()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
       
        //Details 
        Label Date = new Label("Edité le : " +A.getDate_a() + "par "+A.getEDITEUR_A()) ; 
        
        Label Type = new Label("Type :"+A.getTYPE_A()) ;
        Type.setTextFill(Color.web("#17202A"));
        //Voir Plus
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #17202A"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+A.getID_ARTICLE());
           button.onActionProperty().set((event) -> {
            try { 
                ListArticle.setContent(AfficheEvent (button.getAccessibleText()));
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
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Article", A.getID_ARTICLE())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(Date,Type) ;
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
    public  Node ListFiltreArticle (String type) throws SQLException {

        VBox root0 =new VBox(10) ; 

        ObservableList<Article> OB = FXCollections.observableArrayList() ; 
        OB = CA.searchByType(type); 
        
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        
        root.setStyle("-fx-background-color:  #2471A3"); 
       root.setPadding(new Insets(5,5, 5,5));

        try {
            Article A = OB.get(i)   ;
        Image image = new Image("file:"+A.getAFFICHE_A());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        
        
        //Titre
        Label Titre = new Label(A.getTITRE_A()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        //Description 
        Label Des = new Label(A.getDESCRIPTION_A()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
       
        //Details 
        Label Date = new Label("Edité le : " +A.getDate_a() + "par "+A.getEDITEUR_A()) ; 
        
        Label Type = new Label("Type :"+A.getTYPE_A()) ;
        Type.setTextFill(Color.web("#17202A"));
        //Voir Plus
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #17202A"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+A.getID_ARTICLE());
           button.onActionProperty().set((event) -> {
            try { 
                ListArticle.setContent(AfficheEvent (button.getAccessibleText()));
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
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Article", A.getID_ARTICLE())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(Date,Type) ;
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
     public  Node ListSearchArticle (String e) throws SQLException {

        VBox root0 =new VBox(10) ; 

        ObservableList<Article> OB = FXCollections.observableArrayList() ; 
        OB = CA.searchByNameArticle(e); 
        
        for (int i=0; i < OB.size() ; i++)  {
 
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        
        root.setStyle("-fx-background-color:  #2471A3"); 
       root.setPadding(new Insets(5,5, 5,5));

        try {
            Article A = OB.get(i)   ;
        //FileInputStream input = new FileInputStream("C:\\Users\\CePc\\Desktop\\20900983_2022925027733628_8604185357246447266_o.jpg");
        Image image = new Image("file:"+A.getAFFICHE_A());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        VBox root2 = new VBox(6) ;
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4,4, 4,4));
        
        
        //Titre
        Label Titre = new Label(A.getTITRE_A()) ; 
        Titre.setFont(new Font("Arial", 30));
        Titre.setTextFill(Color.web("#17202A"));
       
        //Description 
        Label Des = new Label(A.getDESCRIPTION_A()) ;
        Des.backgroundProperty().set(Background.EMPTY);
        Des.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 =new Separator(Orientation.HORIZONTAL) ;
       
        //Details 
        Label DateDebut = new Label("Edité le : " +A.getDate_a() + "par "+A.getEDITEUR_A()) ; 
        
        Label Type = new Label("Type :"+A.getTYPE_A()) ;
        Type.setTextFill(Color.web("#17202A"));
        //Voir Plus
             HBox Hbtn = new HBox(10);
           Button button = new Button("Voir Plus d'information") ;
           button.setStyle("-fx-background-color:  #17202A"); 
           button.setTextFill(Color.web("#FBFCFC"));
           button.setAccessibleText(""+A.getID_ARTICLE());
           button.onActionProperty().set((event) -> {
            try { 
                ListArticle.setContent(AfficheEvent (button.getAccessibleText()));
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
           
        Label MoyNote = new Label(""+ CEval.MoyenneEvaluation("Article", A.getID_ARTICLE())) ;
        MoyNote.setTextFill(Color.web("#17202A"));
        
        root.getChildren().addAll(IMG,sep,root2) ;
        Hbtn.getChildren().addAll(Des,button) ;
        root2.getChildren().addAll(Titre,Hbtn,sep3,root3,HNote) ;
        root3.getChildren().addAll(DateDebut,Type) ;
        HNote.getChildren().addAll(Note,MoyNote) ;
        
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        root0.getChildren().add(root) ;
     root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0,0,0,0));
    }
       
     return   root0 ;
    }
 
    public Node AfficheEvent (String id) throws SQLException, FileNotFoundException {
      A =  CA.searchByID_Article(Integer.parseInt(id)) ;
       
      VBox V0 = new VBox(10) ;
      HBox H0 = new HBox(6) ; 
      VBox V2 = new VBox(10) ; 
        Image image = new Image("file:"+A.getAFFICHE_A());
        ImageView IMG = new ImageView(image);
       
         HBox Hnote = new HBox(10) ; 
         Label Titre = new Label("") ;  
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
         SubmitNote.accessibleTextProperty().set(""+A.getID_ARTICLE());
        
       
         
         
         
        VBox V1 = new VBox(10) ;
        
        Button btnExit = new Button("< retour ") ;
        btnExit.setStyle("-fx-background-color:  #2471A3 ; -fx-font-weight: bold" );
        btnExit.setTextFill(Color.web("#FDFEFE"));
        btnExit.onActionProperty().set((event) -> {
          try {
              ListArticle.setContent(ListofAllArticle());
          } catch (SQLException ex) {
          
          }
        }) ; 
        //Titre
        HBox H1 = new HBox(8) ; 
        
        Label Titre1  = new Label(A.getTITRE_A()) ; 
        
        Titre1.setFont(new Font("Arial", 30));
        TextArea Descrip = new TextArea(A.getDESCRIPTION_A()) ; 
        //Description
        Descrip.setFont(new Font("Arial", 14));
        Descrip.editableProperty().set(false);
        //Detaille
        HBox H2 = new HBox(10) ; 
        Label Date = new Label("Date  : " +A.getDate_a()) ; 
        Date.setFont(new Font("Arial", 14));
        Label DateFin = new Label("Editeur : " +A.getEDITEUR_A()) ; 
        DateFin.setFont(new Font("Arial", 14));
        
        Label Type = new Label("Type : "+A.getTYPE_A()) ;
        Type.setFont(new Font("Arial", 14));
        H1.getChildren().addAll(btnExit,Titre1) ;
        H1.setAlignment(Pos.CENTER_LEFT);  
        H2.getChildren().addAll(Date) ;
        V1.getChildren().addAll(H1,Descrip,H2,Type) ; 
       
        IMG.fitHeightProperty().set(image.getHeight()/7);
        IMG.fitWidthProperty().set(image.getWidth()/7);
        Separator sep = new Separator(Orientation.VERTICAL) ; 
        Hnote.getChildren().addAll(NoteL,N1,N2,N3,N4,N5,NoteValue,SubmitNote) ;
//        V2.getChildren().addAll(IMG,Hnote);
//        H0.getChildren().addAll(V1,sep,V2) ;
//        V0.getChildren().addAll(H0) ;
//        
       
         
       TextArea Comment = new TextArea() ; 
       Comment.setPromptText("Commenter");
       Comment.setMaxWidth(300);
      Comment.setMaxHeight(100);
       ScrollPane comm = new ScrollPane() ; 
        ObservableList<Evaluation> EvalU = CEval.commentAndNoteByuser("Article" ,A.getID_ARTICLE()) ; 
        VBox ListComm = new VBox(7) ; 
        ListComm.fillWidthProperty().set(true);
         
        for (int i=0;i<EvalU.size();i++)  {
            System.out.println("entities.FXML.AcceuilController.AfficheEvent()");
        HBox Hcomm = new HBox(6) ; 
        
        VBox Vcomm = new  VBox() ; 
        
        ImageView Imgcomm = new ImageView("file:\"+A.getAFFICHE_A()") ;
        Imgcomm.setFitHeight(50);
        Imgcomm.setFitWidth(50);
        Label UserNameCom = new Label("ID Utilisateur "+EvalU.get(i).getID_UTILISATEUR()) ; 
        UserNameCom.setFont(new Font("Arial", 16));
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
             Evaluation Eval = new Evaluation(0, CurrentUser.getId(), Integer.parseInt(NoteValue.getText()) , Comment.getText() ,"Article" , A.getTYPE_A() , A.getID_ARTICLE(), A.getDate_a()) ;
             CrudEvaluation IsertNote = new CrudEvaluation(); 
             
         try {
              IsertNote.insertEvaluation(Eval);
             System.out.println("Evamuation Donne  ");
              
                 try {
                     ListArticle.setContent(AfficheEvent(String.valueOf(A.getID_ARTICLE())));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
            
            try {
                ListArticle.setContent(ListofAllArticle());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            InitHoroscope();
            
          

         WebView embeddedWV = new WebView();
           WebEngine WebView = embeddedWV.getEngine();
           WebViewLeft.getEngine().load("https://www.youtube.com/watch?v=Vo7QtHdeTbo&list=PL_C0q8jNRXJadoakiLVd8Prvu_HbZkKkm&index=2");
           
         WebView embeddedWV1 = new WebView();
           WebEngine WebView1 = embeddedWV.getEngine();
           WebViewright.getEngine().load("https://www.youtube.com/watch?v=rq39plBJJl8&list=PL_C0q8jNRXJYpk5Thodl9iTOq16mydkYS");
    }
  

    @FXML
    private void ConfirmationHoroscope(ActionEvent event) throws MalformedURLException, IOException, JSONException, SQLException {
        
       
      try{
         
        
          
          
          

      int year= Integer.parseInt(id_year.getText()) ;
      int Month= Integer.parseInt(id_month.getText());
      int day=  Integer.parseInt(id_day.getText());
          
      
        String sign = null;
 if(year < 1900 || year >2018 || id_year.getText().equals("")){
       
     
alert.setTitle("Error Dialog");
alert.setHeaderText("Invalid year number");
alert.setContentText("years are between 1900-2018 !");
alert.showAndWait();

id_year.setStyle("-fx-border-color : red ;");
            
 }
 if (Month < 1 || Month > 12 || id_month.getText().equals("")) {   
     
     alert.setTitle("Error Dialog");
alert.setHeaderText("Invalid month number");
alert.setContentText("Months are between 1-12 !");
alert.showAndWait();
id_month.setStyle("-fx-border-color : red ;");
 }  
 else if (day < 1 || day > 31 || id_day.getText().equals("")) { 
      
      alert.setTitle("Error Dialog");
        alert.setHeaderText("Invalid day number");
        alert.setContentText("Days are between 1-31 !");
        alert.showAndWait();
        id_day.setStyle("-fx-border-color : red ;");
 } 
 else if (Month == 1) { 
     if (day <= 19) { 
  sign = "Capricorn"; 
     } 
     else { 
  sign = "Aquarius"; 
     } 
 } 
 else if (Month == 2) { 
     if (day <= 18) { 
  sign = "Aquarius"; 
     } 
     else { 
  sign = "Pisces"; 
     } 
 } 
 else if (Month == 3) { 
     if (day <= 20) { 
  sign = "Pisces"; 
     } 
     else { 
  sign = "Aries"; 
     } 
 } 
 else if (Month == 4) { 
     if (day <= 19) { 
  sign = "Aries"; 
     } 
     else { 
  sign = "Taurus"; 
     } 
 } 
 else if (Month == 5) { 
     if (day <= 20) { 
  sign = "Taurus"; 
     } 
     else { 
  sign = "Gemini"; 
     } 
 } 
 else if (Month == 6) { 
     if (day <= 21) { 
  sign = "Gemini"; 
     } 
     else { 
  sign = "Cancer"; 
     } 
 } 
 else if (Month == 7) { 
     if (day <= 22) { 
  sign = "Cancer"; 
     } 
     else { 
  sign = "Leo"; 
     } 
 } 
 else if (Month == 8) { 
     if (day <= 22) { 
  sign = "Leo"; 
     } 
     else { 
  sign = "Virgo"; 
     } 
 } 
 else if (Month == 9) { 
     if (day <= 22) { 
  sign = "Virgo"; 
     } 
     else { 
  sign = "Libra"; 
     } 
 } 
 else if (Month == 10) { 
     if (day <= 23) { 
  sign = "Libra"; 
     } 
     else { 
  sign = "Scorpio"; 
     } 
 } 
 else if (Month == 11) { 
     if (day <= 21) { 
  sign = "Scorpio"; 
     } 
     else { 
  sign = "Sagittarius"; 
     } 
 } 
 else { 
     if (day <= 21) { 
  sign = "Sagittarius"; 
     } 
     else { 
  sign = "Capricorn"; 
     } 
 } 

        String url =String.format("http://horoscope-api.herokuapp.com/horoscope/today/"+sign);
        
        URL obj = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        // optional default is GET
        con.setRequestMethod("GET");
        
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
        	response.append(inputLine);
        }
        in.close();
        id_signe.setText("Your sign is : " + sign);
        
        id_daily.setText("Your Daily Horoscope : ");
        int pos_start=response.toString().indexOf("\"horoscope\":");
        int pos_end=response.toString().indexOf(",   \"sunsign\"");
        id_texthoroscope.setText(response.toString().substring(pos_start+14, pos_end-1));
          
        
        
        //Read JSON response and print
        
        JSONObject myResponse = new JSONObject(response.toString());
     
              }catch(NumberFormatException ex){
          alert.setTitle("Error Dialog");
          alert.setHeaderText("Invalid Input");
          alert.setContentText("You must write correctly your birthday !");
          alert.showAndWait();
           if(id_year.getText().equals("")){id_year.setStyle("-fx-border-color : red ;");}else{id_year.setStyle("-fx-border-color : black ;");}
             if(id_month.getText().equals("")){id_month.setStyle("-fx-border-color : red ;");}else{id_month.setStyle("-fx-border-color : black ;");}
             if(id_day.getText().equals("")){id_day.setStyle("-fx-border-color : red ;");}else{id_day.setStyle("-fx-border-color : black ;");}
          
}
    }   

    private void getUtilisateurId(Integer id_Utilisateur) 
{

int idHolder = id_Utilisateur;
    
}

    @FXML
    private void Filtrer(ActionEvent event) throws SQLException {
             String typ = "''";
        if(!(Sport.isSelected()||Santé.isSelected()||Médecine.isSelected()||Nutrition.isSelected()||Thérapie.isSelected()||Horoscope.isSelected()||Citation.isSelected()||Psychologie.isSelected()||Motivation.isSelected()))
        {ListArticle.setContent(ListofAllArticle());}else{
        if(Sport.isSelected() ){typ+=" OR TYPE_A LIKE 'Sport'" ;} 
        if(Santé.isSelected()){typ+=" OR TYPE_A LIKE 'Santé'";} 
        if(Médecine.isSelected()){typ+=" OR TYPE_A LIKE 'Médecine' ";}
        if(Nutrition.isSelected()){typ+=" OR TYPE_A LIKE 'Nutrition' ";}
        if(Thérapie.isSelected() ){typ+=" OR TYPE_A LIKE 'Thérapie'" ;} 
        if(Horoscope.isSelected()){typ+=" OR TYPE_A LIKE 'Horoscope'";} 
        if(Citation.isSelected()){typ+=" OR TYPE_A LIKE 'Citation' ";}
        if(Psychologie.isSelected()){typ+=" OR TYPE_A LIKE 'Psychologie' ";}
        if(Motivation.isSelected()){typ+=" OR TYPE_A LIKE 'Motivation' ";}
       
try {
            ListArticle.setContent(ListFiltreArticle(typ));
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }
          
           
    }

    @FXML
    private void Recherche(KeyEvent event) {
           try {
            ListArticle.setContent(ListSearchArticle(chercher.getText().trim()));
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void ChangerdateH(MouseEvent event) {
         id_texthoroscope.clear();
        id_signe.clear();
        id_signe.setText("Your sign is : ");
        enterphrase.setVisible(true);
        id_year.setVisible(true);
        id_month.setVisible(true);
        id_day.setVisible(true);
        slache.setVisible(true);
        slache1.setVisible(true);
        AnnulerConfirmation.setVisible(true);
        btnConfirmation.setVisible(true);
        
        ChangerdateH.setVisible(false);
    }
    
    public void InitHoroscope()
    {
 try{               
enterphrase.setVisible(false);
id_year.setVisible(false);
id_month.setVisible(false);
id_day.setVisible(false);
slache.setVisible(false);
slache1.setVisible(false);
btnConfirmation.setVisible(false);
AnnulerConfirmation.setVisible(false);
ChangerdateH.setVisible(true);
     
Utilisateur d=LoginGUIController.CurrentUser;


     

int year1=Integer.parseInt( d.getDate_naissance().toString().substring(0, 4)) ;
int Month1= Integer.parseInt( d.getDate_naissance().toString().substring(5, 7));
int day1= Integer.parseInt (d.getDate_naissance().toString().substring(8, 10));


String sign = null;

            switch (Month1) {
                case 1:
                    if (day1 <= 19) {
                        sign = "Capricorn";
                    }
                    else {
                        sign = "Aquarius";
                    }               break;
                case 2:
                    if (day1 <= 18) {
                        sign = "Aquarius";
                    }
                    else {
                        sign = "Pisces";
                    }               break;
                case 3:
                    if (day1 <= 20) {
                        sign = "Pisces";
                    }
                    else {
                        sign = "Aries";
                    }               break;
                case 4:
                    if (day1 <= 19) {
                        sign = "Aries";
                    }
                    else {
                        sign = "Taurus";
                    }               break;
                case 5:
                    if (day1 <= 20) {
                        sign = "Taurus";
                    }
                    else {
                        sign = "Gemini";
                    }               break;
                case 6:
                    if (day1 <= 21) {
                        sign = "Gemini";
                    }
                    else {
                        sign = "Cancer";
                    }               break;
                case 7:
                    if (day1 <= 22) {
                        sign = "Cancer";
                    }
                    else {
                        sign = "Leo";
                    }               break;
                case 8:
                    if (day1 <= 22) {
                        sign = "Leo";
                    }
                    else {
                        sign = "Virgo";
                    }               break;
                case 9:
                    if (day1 <= 22) {
                        sign = "Virgo";
                    }
                    else {
                        sign = "Libra";
                    }               break;
                case 10:
                    if (day1 <= 23) {
                        sign = "Libra";
                    }
                    else {
                        sign = "Scorpio";
                    }               break;
                case 11:
                    if (day1 <= 21) {
                        sign = "Scorpio";
                    }
                    else {
                        sign = "Sagittarius";
                    }               break;
                default:
                    if (day1 <= 21) {
                        sign = "Sagittarius";
                    }
                    else {
                        sign = "Capricorn";
                    }               break;
            } 

String url1 =String.format("http://horoscope-api.herokuapp.com/horoscope/today/"+sign);

URL obj = new URL(url1);

HttpURLConnection con = (HttpURLConnection) obj.openConnection();

// optional default is GET
con.setRequestMethod("GET");

//add request header
con.setRequestProperty("User-Agent", "Mozilla/5.0");

int responseCode = con.getResponseCode();

BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
        in.close();
        id_signe.setText("Your sign is : " + sign);
        

       int pos_start=response.toString().indexOf("horoscope\": \"");
       int pos_end=response.toString().indexOf("\",   \"sunsign\"");
       id_texthoroscope.setText(response.toString().substring(pos_start+14,pos_end-1));
                
         } catch (IOException ex) {
             alert.setTitle("Error Dialog");
             alert.setHeaderText("Pas de Connexion Internet Disponible");
             alert.setContentText("Veillez Verifier votre connexion !");
             alert.showAndWait();
         }
    }
    

    @FXML
    private void AnnulerConfirmation(ActionEvent event) {
        
        id_texthoroscope.clear();
        id_signe.clear();
        enterphrase.setVisible(false);
        id_year.setVisible(false);
        id_month.setVisible(false);
        id_day.setVisible(false);
        slache.setVisible(false);
        slache1.setVisible(false);
        btnConfirmation.setVisible(false);
        ChangerdateH.setVisible(true);
        AnnulerConfirmation.setVisible(false);
        InitHoroscope();
    }


    
  
   
    
}

