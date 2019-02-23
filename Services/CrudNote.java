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
import Services.CrudEvaluation;

/**
 *
 * @author LENOVO
 */
public class CrudNote {
      Connection con = DataSource.getInstance().getConnection();
    Statement ste;
    PreparedStatement pst;
    ResultSet rs;

    public void insertNote(Evaluation e) throws SQLException{
     String requete = "INSERT INTO Evaluation (NOTES_EV) values"
                + " e.getNOTES_EV() ";

        ste = con.createStatement();
        ste.executeUpdate(requete);
    }
    
          public void UpdateEvaluation(Evaluation e) throws SQLException{
        String requete="UPDATE Evaluation SET NOTES_EV=? where" ;
       
        pst=con.prepareStatement(requete) ; 
        pst.setInt(3, e.getNOTES_EV());
        pst.executeUpdate() ; 
       
    }
         public ObservableList<Evaluation> SearchByNom(String objett, String nom) throws SQLException {
        if (objett.equals("offre")) {
            String requete = "SELECT AVG(NOTES_EV)FROM Evaluation WHERE id_o=(select  ID_OFFRE from offre where  titre_o='" + nom + "' ) ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
        } else if (objett.equals("espace")) {
            String requete = "SELECT AVG(NOTES_EV)FROM Evaluation WHERE id_o=(select  ID_ESPACE from espace where  nom_es='" + nom + "') ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
        } else if (objett.equals("evenement")) {

            String requete = "SELECT AVG(NOTES_EV)FROM Evaluation WHERE id_o=(select  ID_EVENEMENT from evenement where  titre_e='" + nom + "') ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
        }else if (objett.equals("article")) {

            String requete = "SELECT AVG(NOTES_EV)FROM Evaluation WHERE id_o=(select  ID_ARTICLE from ARTICLE where  titre_a='" + nom + "') ";
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
        }
        ObservableList<Evaluation> list = FXCollections.observableArrayList();
        while (rs.next()) {
            Evaluation ev = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8));
            list.add(ev);
        }
        return list;
    }
    }

