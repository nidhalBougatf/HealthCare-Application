/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import Services.CrudOffre;
import entities.Offre;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.round;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Nidhal Bougatf
 */
public class OffreController implements Initializable {
   String pathh ="";
    @FXML
    private Button btnappliquer;
    @FXML
    private Button reinitialiser;
    @FXML
    private Button annuler;
    @FXML
    private Button enregistrer;
    @FXML
    private ComboBox<String> nomespace;
    
    private ObservableList<String> myespaces=FXCollections
            .observableArrayList() ;
    private ObservableList<String> myespacess=FXCollections
            .observableArrayList() ;
    
    private int idHolder;
    @FXML
    private TextField titre;
    @FXML
    private TextField pathaffiche;
    @FXML
    private TextArea description;
    @FXML
    private TextField ancienprix;


    @FXML
    private TextField nouveauprix;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
   
    @FXML
    private Button btnupload;
    @FXML
    private Button btnadd;
    
    ObservableList<String> myData = FXCollections
            .observableArrayList("Promotion", "Offre");
    
    
    @FXML
    private Button btnajouter;
    @FXML
    private  Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<Offre> tableoffre;

    
    @FXML
    private TextField titreoffrerech;
    @FXML
    private ComboBox<String> nomespacerech;
    @FXML
    private ComboBox<String> disponibilite;

    @FXML
    private Slider prixminrech;
    @FXML
    private ImageView viewaffiche;
    @FXML
    private Slider prixmaxrech;
    
    private ObservableList<String> mydispo=FXCollections
            .observableArrayList("Tous","Disponible","Epuise") ;
    
    private ObservableList<Offre> list_promotion= FXCollections.observableArrayList();
    
    CrudOffre co = new CrudOffre(); 
    PieChart chart = new PieChart();
    
    boolean selected=false;
    
        TableColumn _nomesp = new TableColumn("Nom espace");
        TableColumn _titre = new TableColumn("Titre");
        TableColumn _description = new TableColumn("Description");
        TableColumn _ancprix = new TableColumn("Ancien prix");
        TableColumn _nvprix = new TableColumn("Nouveau prix");
        TableColumn _startingDate = new TableColumn("Date debut");
        TableColumn _endingDate = new TableColumn("Date fin");
    @FXML
    private VBox stats;

        
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        annuler.setVisible(false);
        enregistrer.setVisible(false);
        btnajouter.setVisible(false);
        btnmodifier.setVisible(true);
        btnsupprimer.setVisible(true);
        btnadd.setVisible(true);

            
            
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
            nomespace.setValue("Select Espace");
            nomespace.setItems(myespaces);
            
            
            nomespacerech.setValue("Tous");
            nomespacerech.setItems(myespaces);
            
            disponibilite.setValue("Tous");
            disponibilite.setItems(mydispo);
            
            
            _nomesp.setCellValueFactory(new PropertyValueFactory<>("nom_es"));
            _titre.setCellValueFactory(new PropertyValueFactory<>("TITRE_O"));
            _description.setCellValueFactory(new PropertyValueFactory<>("DESCRIPTION_O"));
            _ancprix.setCellValueFactory(new PropertyValueFactory<>("ANCIEN_PRIX"));
            _nvprix.setCellValueFactory(new PropertyValueFactory<>("NOUVEAU_PRIX"));
            _startingDate.setCellValueFactory(new PropertyValueFactory<>("DATEDEBUT_O"));
            _endingDate.setCellValueFactory(new PropertyValueFactory<>("DATEFIN_O"));
            tableoffre.getColumns().addAll(_nomesp,_titre,_description,_ancprix,_nvprix,_startingDate,_endingDate);
            buildOffreTable();
            
            
            tableoffre.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Offre> observable,Offre oldValue,Offre newValue) -> {
                    if (newValue == null) {
                        return;
                    }
            try {
                getPromotionInfo(newValue.getID_OFFRE());
            } catch (SQLException ex) {
                System.out.println(ex);            
            }
                });

                statss();

            
       
    }    

     private void buildOffreTable() 
     {
              
        try 
        {
            tableoffre.setItems(co.displayAllOffre());
            
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        selected=false;
    }
     
     
     
     
      public void buildNomEspace()
    {
         
        try {
            
             myespaces =FXCollections.observableArrayList(co.getAllEspace());
             
            
        } catch (SQLException ex) {
            System.out.println(ex); 
        }
    }
      
     @FXML
    private void btnadd(ActionEvent event) throws IOException, SQLException 
    {
        clearsaisie();
        btnadd.setVisible(false);
            enregistrer.setVisible(false);
            btnmodifier.setVisible(false);
            btnajouter.setVisible(true);
            btnsupprimer.setVisible(false);
            annuler.setVisible(true);
            
            titre.setEditable(true); 
            description.setEditable(true);         
            datedebut.setEditable(true); 
            datefin.setEditable(true); 
            pathaffiche.setEditable(true); 
            ancienprix.setEditable(true); 
            nouveauprix.setEditable(true); 
            pathaffiche.setEditable(true);

        

    }
    @FXML
    private void ajouter(ActionEvent event) throws IOException, SQLException 
    {
        

        if(testsaisie())
        {
            
            buildOffreTable(); 
            String nomesp = nomespace.getValue() ; 
            String titr = titre.getText() ;
            String desc = description.getText();
            Double ancprix=Double.parseDouble(ancienprix.getText());
            Double nvprix=Double.parseDouble(nouveauprix.getText());
            LocalDate datedeb=datedebut.getValue();
            LocalDate datef=datefin.getValue();
            String aff=pathaffiche.getText();
            
            //System.out.println("path="+aff);
            
            //String output = String.format("%s = %d", "joe", 35);
            //System.out.println("path="+output);
            
            Offre f = new Offre(nomesp,desc,titr,ancprix,nvprix,datedeb,datef,aff);
            co.InsertOffre(f);
            Alert a = new Alert(Alert.AlertType.INFORMATION) ; 
            a.setContentText("Offre ajouté avec succés !!");
            a.showAndWait();
            buildOffreTable(); 
            clearsaisie();
            annuler.setVisible(false);
        enregistrer.setVisible(false);
        btnajouter.setVisible(false);
        btnmodifier.setVisible(true);
        btnsupprimer.setVisible(true);
        btnadd.setVisible(true);
        stats.getChildren().remove(chart);
        statss();

        }
        

    }
    public void clearsaisie()
    {
            
            titre.clear() ;
            description.clear();        
            datedebut.setValue(null);
            datefin.setValue(null);
            pathaffiche.clear();
            ancienprix.clear();
            nouveauprix.clear();
            nomespace.setValue("Select Espace");
            nomespace.setItems(myespaces);
            
            nomespacerech.setValue("Tous");
            nomespacerech.setItems(myespaces);
            viewaffiche.setImage(null);
    }
    
    public boolean testsaisie()
    {
        
         String nomesp = nomespace.getValue() ; 
            String titr = titre.getText() ;
            String desc = description.getText();
            Double nvprix=0.0;
            Double ancprix=0.0;
            
           
            
            LocalDate datedeb=datedebut.getValue();
            LocalDate datef=datefin.getValue();
            String aff=pathaffiche.getText();

        boolean res=false;
        
            if ( nomesp.equals("Select Espace") )
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez selctioner le nom de l'espace ! ");
                a.showAndWait();
            }
            else if ( titr.equals("") )
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez ajouter un titre ! ");
                a.showAndWait();
            }
            else if (desc.equals(""))
             {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez ajouter une description ! ");
                a.showAndWait();
            }
            else if(datedeb==null)
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez ajouter la date de debut ! ");
                a.showAndWait(); 
            }
            else if(datef== null)
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez ajouter la date de fin ! ");
                a.showAndWait(); 
            }
            else if( LocalDate.now().toEpochDay() >= datedeb.toEpochDay() )
            {
                 Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("la date de l'offre doit etre superieur à la date courant  ");
                a.showAndWait();  
            }
            else if(datef.compareTo(datedeb) <=0)
            {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez verifier les dates ! ");
                a.showAndWait(); 
            }
            else if( nvprix ==0.0 || ancprix ==0.0)
            {
                    try
               {
                   ancprix=Double.parseDouble(ancienprix.getText());
               }
               catch(NumberFormatException o)
               {
                    Alert a = new Alert(Alert.AlertType.WARNING) ; 
                   a.setContentText("L'ancien prix doit etre un réel ! ");
                   a.showAndWait(); 
                   return false;
               }
               try
               {
                   nvprix=Double.parseDouble(nouveauprix.getText());
               }
               catch (NumberFormatException o)
               {
                    Alert a = new Alert(Alert.AlertType.WARNING) ; 
                   a.setContentText("Le nouveau prix doit etre un réel ! ");
                   a.showAndWait(); 
                   return false;
               }
               if(nvprix>=ancprix )
               {
                   Alert a = new Alert(Alert.AlertType.WARNING) ; 
                   a.setContentText("Le nouveau prix doit etre inferieur à l'ancien prix ! ");
                   a.showAndWait(); 
               }
                     
            }
            else if (aff.equals(""))
             {
                Alert a = new Alert(Alert.AlertType.WARNING) ; 
                a.setContentText("Veuillez selectionner une affiche ! ");
                a.showAndWait();
            }   
            else
                res=true;          
        
        
        return res;
    }
    
    @FXML
    public void upload(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir l'affiche");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
         pathh = selectedFile.getAbsolutePath().toString();
        
        if (pathh.equals("")) 
        {
            Alert a = new Alert(Alert.AlertType.WARNING) ; 
            a.setContentText("Veuilez selectionner une affiche !!");
            a.showAndWait();
        }
        else
        {
            pathaffiche.setText(pathh);
        }
        Image img = new Image ("file:"+selectedFile.getAbsolutePath()) ;
        viewaffiche.imageProperty().set(img);
        

    }
    
    @FXML
    public void chercher(KeyEvent event) throws  SQLException 
    {
        
        if ("".equals(titreoffrerech.getText().trim())) 
        {
            buildOffreTable(); 
            
        }
        else
        {
            selected=false;
            String titr = titreoffrerech.getText() ;
                
            try 
            {
                tableoffre.setItems(co.searchOffreByTitre(titr));

            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
            }
 
    
        }
    }
    
    @FXML
    public void appliquer(ActionEvent event) throws IOException, SQLException 
    {
        Double ancprix=prixminrech.getValue();
        Double nvprix=prixmaxrech.getValue();
        String nomespa = nomespacerech.getValue() ;
        String dispoesp = disponibilite.getValue() ;
        
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
                

                list_promotion = FXCollections.observableArrayList();
                try {
                    co.searchOffreMultiple(nomespa,dispoesp,ancprix,nvprix).forEach(e -> { list_promotion.add(e);});
                } catch (SQLException ex) {
                    System.out.println(ex); 
                }

                tableoffre.getItems().clear();
                tableoffre.getItems().addAll(list_promotion);
                
            } 
        }
        else
        {
            

                list_promotion = FXCollections.observableArrayList();
                try {
                    co.searchOffreWithoutprix(nomespa,dispoesp).forEach(e -> { list_promotion.add(e);});
                } catch (SQLException ex) {
                    System.out.println(ex); 
                }

                tableoffre.getItems().clear();
                tableoffre.getItems().addAll(list_promotion);
            
        }
        

        
    }
    
    @FXML
    public void reinitialiser(ActionEvent event)
    {
            
            
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
            nomespacerech.setItems(myespaces);
            nomespacerech.setValue("Tous");
            
            disponibilite.setItems(mydispo);
            disponibilite.setValue("Tous");
            buildOffreTable();
            statss();
        
    }
    @FXML
    public void supprimer(ActionEvent event) throws SQLException
    {
        
        
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Aucun objet selectionné");
            alert.setContentText("Veuillez selectionner une offre à supprimer !");
            alert.showAndWait();
        } 
        else 
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppresion");
            alert.setContentText("Voulez vous vraiment supprimer cette offre ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
                
                co.DeleteOffre(idHolder);
                buildOffreTable();
                clearsaisie();
                viewaffiche.setImage(null);
                stats.getChildren().remove(chart);
                statss();
            }
        }
    }
    
    @FXML
    public void modifier(ActionEvent event) throws SQLException
    {
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Aucun objet selectionné");
            alert.setContentText("Veuillez selectionner une offre à modifier !");
            alert.showAndWait();
        } 
        else 
        {
            
            btnadd.setVisible(false);
            enregistrer.setVisible(true);
            btnmodifier.setVisible(false);
            btnajouter.setVisible(false);
            btnsupprimer.setVisible(false);
            annuler.setVisible(true);
            

            titre.setEditable(true); 
            description.setEditable(true);         
            datedebut.setEditable(true); 
            datefin.setEditable(true); 
            pathaffiche.setEditable(true); 
            ancienprix.setEditable(true); 
            nouveauprix.setEditable(true); 
            
        }
        
    }

    private void getPromotionInfo(Integer id_offre) throws SQLException 
    {
        
            selected = true;
           idHolder = id_offre;
          
           btnadd.setVisible(true);
           annuler.setVisible(false);
            enregistrer.setVisible(false);
            btnmodifier.setVisible(true);
            btnajouter.setVisible(false);
            btnsupprimer.setVisible(true);
            
            
            
            Offre result =  co.getOffreById(idHolder);
            nomespace.getSelectionModel().select(co.getNomEspaceID(result.getID_ESPACE()));
            titre.setText(result.getTITRE_O()); 
            description.setText(result.getDESCRIPTION_O());
            Double x = result.getANCIEN_PRIX();
            Double y = result.getNOUVEAU_PRIX();
            ancienprix.setText(x.toString());
            nouveauprix.setText(y.toString());
            datedebut.setValue(result.getDATEDEBUT_O());
            datefin.setValue(result.getDATEFIN_O());
            
            pathaffiche.setText(result.getAFFICHE_O());
            Image image = new Image(new File(pathaffiche.getText()).toURI().toString());
            viewaffiche.setImage(image);
            

            titre.setEditable(false); 
            description.setEditable(false);         
            datedebut.setEditable(false); 
            datefin.setEditable(false); 
            pathaffiche.setEditable(false); 
            ancienprix.setEditable(false); 
            nouveauprix.setEditable(false); 
        
    }
    
    @FXML
    public void annuler (ActionEvent event)
    {
        clearsaisie();
        
           annuler.setVisible(false);
        enregistrer.setVisible(false);
        btnajouter.setVisible(false);
        btnmodifier.setVisible(true);
        btnsupprimer.setVisible(true);
        btnadd.setVisible(true);
        viewaffiche.setImage(null);
        
        
        
    }
    @FXML
    public void enregistrer(ActionEvent event) throws SQLException
    {
       
        
        String nomesp = nomespace.getValue() ; 
        String titr = titre.getText() ;
        String desc = description.getText();
        Double ancprix=Double.parseDouble(ancienprix.getText());
        Double nvprix=Double.parseDouble(nouveauprix.getText());
        LocalDate datedeb=datedebut.getValue();
        LocalDate datef=datefin.getValue();
        String aff=pathaffiche.getText();
        Offre result =  co.getOffreById(idHolder);
        int idesp = co.getIDNomEspace(nomesp);
        System.out.println("ides"+idesp);
        Offre f = new Offre(idHolder,idesp,desc,titr,ancprix,nvprix,datedeb,datef,aff);
         co.UpdateOffre(f);

         
         Alert a = new Alert(Alert.AlertType.INFORMATION) ; 
            a.setContentText("La modification a été faite !");
            a.showAndWait();
         buildOffreTable();

        clearsaisie();
        annuler.setVisible(false);
        enregistrer.setVisible(false);
        btnajouter.setVisible(false);
        btnmodifier.setVisible(true);
        btnsupprimer.setVisible(true);
        btnadd.setVisible(true);
        viewaffiche.setImage(null);
        stats.getChildren().remove(chart);
        statss();
        
        
    }
    
    
    
    public void statss() 
    {
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<String,Double> maps = new HashMap<>();
       try {
           co.statistiquesOffre(maps);
       } catch (SQLException ex) {
           System.out.println("stat error"+ex);       
       }



        maps.entrySet().stream().map((en) -> {pieChartData.add(new PieChart.Data(en.getKey()+" = "+round( en.getValue().doubleValue()) + "%",round( en.getValue().doubleValue())));return en;})
                .forEachOrdered((en) -> { System.out.println("key "+en.getKey()+"val " + round( en.getValue().doubleValue())); });
        
        chart.setData(pieChartData);
        chart.setTitle("Statistiques des Offres");
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);

        stats.getChildren().addAll(chart) ;
        
    }
}
