/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import connexion.DataSource;
import entities.Evaluation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public class CrudEvaluation {

    Connection con = DataSource.getInstance().getConnection();
    Statement ste;
    PreparedStatement pst;
    ResultSet rs;

    public void insertEvaluation(Evaluation e) throws SQLException {

        String requete = "INSERT INTO Evaluation (ID_EVALUATION,ID_UTILISATEUR,NOTES_EV,COMMENTAIRE_EV,OBJET_EV, TYPE_EV, ID_O, DATE_EVALUATION) values"
                + "('" + e.getID_EVALUATION() + "','" + e.getID_UTILISATEUR() + "','" + e.getNOTES_EV() + "','" + e.getCOMMENTAIRE_EV() + "','" + e.getOBJET_EV() + "','" + e.getTYPE_EV() + "','" + e.getID_O() + "','" + e.getDATE_EVALUATION() + "')";

        ste = con.createStatement();
        ste.executeUpdate(requete);
    }

    public ObservableList<Evaluation> DisplayAll() throws SQLException {
        String requete = "SELECT  * from evaluation ";
        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }

    public ObservableList<Evaluation> searchByNomU(String nom) throws SQLException {
        String requete = "select * FROM Evaluation,utilisateur WHERE  utilisateur.nom Like '%" + nom + "%' and  evaluation.ID_UTILISATEUR = utilisateur.ID_UTILISATEUR";
        System.out.println(requete);
        ste = con.createStatement();
        rs = ste.executeQuery(requete);
        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }
    
    public ObservableList<Evaluation> SearchByType(String type) throws SQLException {

        String requete = "select  * from evaluation " + type + " ";
        System.out.println(requete);
        ste = con.createStatement();
        rs = ste.executeQuery(requete);

        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }
    
     public double MoyenneEvaluation(String objett, int id) throws SQLException {
              double  Moy = 0 ;
            String requete = "SELECT AVG(NOTES_EV)FROM Evaluation WHERE id_o= '"+id+ "'  and OBJET_EV ='"+objett+"' ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
           while (rs.next()) {
              Moy = rs.getInt(1) ;
         }
            
            return Moy ; 
    }
    
     
     public  ObservableList<Evaluation> commentAndNoteByuser (String objett, int id) throws SQLException {
        
          ObservableList<Evaluation> list = FXCollections.observableArrayList();
              String requete = "SELECT * FROM Evaluation WHERE  id_o= '"+id +"' AND OBJET_EV ='"+objett+"'   ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
            
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            
            list.add(ev);
        }

        return list;
    }
  
    public ObservableList<Evaluation> SearchByNomObj( String nom) throws SQLException {
        try {
            String requete = "SELECT * FROM evaluation  where evaluation.OBJET_EV Like '" + nom + "%'";
        System.out.println(requete);
        ste = con.createStatement();
        rs = ste.executeQuery(requete);
      
        } catch (Exception e) {
            System.out.println("Services.CrudEvaluation.SearchByNomU_Type()"+e);
        }
        
        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }

    public void DeleteEvaluation(Evaluation e) throws SQLException {
        String requete = "DELETE FROM Evaluation WHERE id=" + e.getID_EVALUATION();
        ste = con.createStatement();
        ste.executeUpdate(requete);
    }
//
//    public List<Evaluation> DisplayAll2() throws SQLException {
//        String requete = "SELECT  * from evaluation ";
//        ste = con.createStatement();
//        rs = ste.executeQuery(requete);
//        List<Evaluation> list = new ArrayList<>();
//        while (rs.next()) {
//            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
//            list.add(ev);
//        }
//        return list;
//    }

}


