/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.CrudEvaluation;
import connexion.DataSource;
import entities.Evaluation;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;




/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionEvalController implements Initializable {

    @FXML
    private TableView<Evaluation> TableEval;
    @FXML
    private TextField chercherUti;
    @FXML
    private BarChart<String, Number> chart5;
    @FXML
    private RadioButton EvalEvent;
    @FXML
    private RadioButton EvalArticle;
    @FXML
    private RadioButton EvalEspace;
    @FXML
    private RadioButton EvalOffre;
    private TextField NomUser;

    private ObservableList<Evaluation> list_evaluation = FXCollections.observableArrayList();
    ;
 //private final Stage primaryStage;
    
     Alert alert = new Alert(Alert.AlertType.WARNING);
    CrudEvaluation CE = new CrudEvaluation();

    private ObservableList<BarChart.Data> ObArrL_Evals;
    @FXML
    private ToggleGroup filter1;
    @FXML
    private RadioButton EvalNone;
    @FXML
    private TextField NomObj;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UpdateListe();
        

        TableColumn _IdEval = new TableColumn("ID_EVALUATION");
        TableColumn _IdUtilisateur = new TableColumn("ID_UTILISATEUR");
     _IdEval.setVisible(false);
     _IdUtilisateur.setVisible(false);
    
     TableColumn _NotesEv = new TableColumn("NOTES_EV");
        TableColumn _CommentaireEv = new TableColumn("COMMENTAIRE_EV");
        TableColumn _ObjetEv = new TableColumn("OBJET_EV");
        TableColumn _TypeEv = new TableColumn("TYPE_EV");
        TableColumn _IdObj = new TableColumn("ID_O");

        _IdEval.setCellValueFactory(new PropertyValueFactory<>("ID_EVALUATION"));
        _IdUtilisateur.setCellValueFactory(new PropertyValueFactory<>("ID_UTILISATEUR"));
        _NotesEv.setCellValueFactory(new PropertyValueFactory<>("NOTES_EV"));
        _CommentaireEv.setCellValueFactory(new PropertyValueFactory<>("COMMENTAIRE_EV"));
        _ObjetEv.setCellValueFactory(new PropertyValueFactory<>("OBJET_EV"));
        _TypeEv.setCellValueFactory(new PropertyValueFactory<>("TYPE_EV"));
        _IdObj.setCellValueFactory(new PropertyValueFactory<>("ID_O"));
        TableEval.getColumns().addAll(_IdEval, _IdUtilisateur, _NotesEv, _CommentaireEv, _ObjetEv, _TypeEv, _IdObj);



        this.LoadChart("génerale", "");
           

    }

    public void LoadChart(String chartName, String type) {
        String requete = "select Distinct Extract(YEAR from DATE_EVALUATION) as year From Evaluation " + type + "";
        chart5.setTitle("Evaluation " + chartName);

        XYChart.Series<String, Number> series = new XYChart.Series();
        series.setName("Evaluation");

        try {
            Connection connexion = DataSource.getInstance().getConnection();
            ResultSet rs = connexion.createStatement().executeQuery(requete);
            while (rs.next()) {
                String req;
                if (type.equals("")) {
                    req = "select count(*) as number From Evaluation where "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                } else {
                    req = "select count(*) as number From Evaluation " + type + " and "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                }
                ResultSet rs1 = connexion.createStatement().executeQuery(req);
                rs1.next();
                series.getData().add(new XYChart.Data(rs.getString(1), rs1.getInt(1)));
            }
            chart5.getData().clear();
            chart5.getData().add(series);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void LoadChart2(String chartName, String objet) {
        String requete = " select Distinct Extract(YEAR from DATE_EVALUATION) as year From Evaluation " + objet + "";
        chart5.setTitle("Evaluation " + chartName);

        XYChart.Series<String, Number> series = new XYChart.Series();
        series.setName("Evaluation");

        try {
            Connection connexion = DataSource.getInstance().getConnection();
            ResultSet rs = connexion.createStatement().executeQuery(requete);
            while (rs.next()) {
                String req;
                if (objet.equals("")) {
                    req = "select count(*) as number From Evaluation where "
                            + "Extract(YEAR from DATE_EVALUATION) = " + rs.getString(5);
                } else {
                    req = "select count(*) as number From Evaluation " + objet + " and "
                            +"Extract(YEAR from DATE_EVALUATION) = " + rs.getString(1);
                }
                ResultSet rs1 = connexion.createStatement().executeQuery(req);
                rs1.next();
                series.getData().add(new XYChart.Data(rs.getString(5), rs1.getInt(1)));
            }
            chart5.getData().clear();
            chart5.getData().add(series);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    public void UpdateListe() {

        CrudEvaluation CE = new CrudEvaluation();
        try {

            TableEval.setItems(CE.DisplayAll());

        } catch (SQLException ex) {
          
        }
    }

    @FXML
    private void Chercher(ActionEvent event) {

        if ("".equals(chercherUti.getText())) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir un utilisateur! ");
            a.showAndWait();

        } else {
           
            CrudEvaluation CE = new CrudEvaluation();

            list_evaluation = FXCollections.observableArrayList();
            try {
                TableEval.setItems(CE.searchByNomU(chercherUti.getText()));
             
            } catch (SQLException ex) {
              
            }

        }
    }

    @FXML
    private void filtrer(ActionEvent event) throws SQLException {
        String type = "";
        String evalName = "génerale";
        if (EvalEvent.isSelected()) {
            type += "WHERE EVALUATION.TYPE_EV = 'evenement'  ";
            
            evalName = "evenement";
        }
        if (EvalArticle.isSelected()) {
            type += "WHERE EVALUATION.TYPE_EV = 'article' ";
            evalName = "article";
        }
        if (EvalEspace.isSelected()) {
            type += "WHERE EVALUATION.TYPE_EV = 'espace' ";
            evalName = "espace";
        }
        if (EvalOffre.isSelected()) {
            type += "WHERE EVALUATION.TYPE_EV = 'offre' ";
            evalName = "offre";
        }
        try {
            TableEval.setItems(CE.SearchByType(type));
            this.LoadChart(evalName, type);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    @FXML
    private void filtrerObj(ActionEvent event) {
        String objet = NomObj.getText();
      String nom="";
       String evalname="génerale";
       
          if ("".equals(objet))
         {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir un objet! ");
            a.showAndWait();

         } 
         else {
           
//        list_evaluation = FXCollections.observableArrayList();
         if ("offre".equals(objet)) { 
            nom+=" where evaluation.OBJET_EV Like '" + nom + "%'";    
           evalname="offre";}
       
       if (objet.equals("espace")) { 
            nom+=" where evaluation.OBJET_EV Like '" + nom + "%'";      
           evalname="espace";}
       
       if (objet.equals("evenement")) { 
                
           evalname="evenement";}
       
       if (objet.equals("article")) { 
               
           evalname="article";}
 
         }
       
        try {
         TableEval.setItems(CE.SearchByNomObj(objet));
            this.LoadChart2(evalname, objet);
        } catch (SQLException ex) {
          
        }
    }

  @FXML
    private void Reinitialiser(ActionEvent event) {
        
        UpdateListe();
        chercherUti.clear();
        NomObj.clear();
        
        
    }
public void AfficherHeures(int heures){

Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
    

}
    @FXML
    private void AfficheNotif(ActionEvent event) {
       /* 
      Notifications notificationBuilder = Notifications.create()
              .title("Evaluation faite")
              .text("Nombre des evaluations pendant les 6 derniers heures")
              .graphic(null)
              .hideAfter(Duration.hours(6))
              .position(Pos.CENTER)
              .onAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              System.out.println("afficher notification");
              AfficherHeures(6);
              
          }
      });
      notificationBuilder.show();*/
    }

    
}
