/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entite.Espace;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import connexion.DataSource;
import javafx.scene.control.Alert;

/**
 *
 * @author ELYES
 */
public class CrudEspace {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    ResultSet rs;
    
    public CrudEspace() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
        public void insertPST(Espace p) throws SQLException
    {
        String req = "insert into espace ( NOM_ES ,ADRESSE_ES, EMAIL_ES , NUMTEL_ES , FACEBOOK_ES , LOGO_ES,TYPE_ES ) values (?,?,?,?,?,?,?)";
      PreparedStatement  pst = con.prepareStatement(req);//attente des parametre
        pst.setString(1, p.getNomEspace());
        pst.setString(2, p.getADRESSE_ES());
        pst.setString(3, p.getEMAIL_ES());
        pst.setInt(4, p.getNUMTEL_ES());
        pst.setString(5, p.getFACEBOOK_ES());
        pst.setString(6, p.getLOGO_ES());
        pst.setString(7, p.getTYPE_ES());
       
       // ste = connexion.createStatement();
        pst.executeUpdate();
    }
       // ObservableList<Object>
        public List<Espace> displayAll() throws SQLException
    {
        String requete = "select *  from espace";
        ste = con.createStatement();
        
        rs=ste.executeQuery(requete);
        
        List<Espace> List= new ArrayList<>();
        while(rs.next())
        {
            Espace p = new Espace(rs.getString(3),rs.getString(4));
            List.add(p);
        }
        return List;
    }
                public ObservableList<Espace> displayAllObs() throws SQLException
    {
        String requete = "select *  from espace";
        ste = con.createStatement();
        
        
        rs=ste.executeQuery(requete);
        
        ObservableList<Espace> List= FXCollections.observableArrayList();;
        while(rs.next())
        {
            Espace p = new Espace(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
            List.add(p);
        }
        return List;
    }
                 public ObservableList<Espace> SearchByType(String type) throws SQLException
    {
        String requete = "select *  from espace where TYPE_ES = '"+type+"'";
        ste = con.createStatement();
        
        rs=ste.executeQuery(requete);
        
        ObservableList<Espace> List= FXCollections.observableArrayList();;
        while(rs.next())
        {
            Espace p = new Espace(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
            List.add(p);
        }
        return List;
    }
                    public void deleteST(int id) throws SQLException
    {
        String req = "Delete from espace where ID_ESPACE= "+id;
        ste =con.createStatement();
        ste.executeUpdate(req);
    }
        public ObservableList<Espace> getEspace()
        {
            ObservableList<Espace>espaces= FXCollections.observableArrayList();
            Espace e = new Espace("test","test","test",468468,"test","test","test");
            espaces.add(e);
            
            return espaces;
        }
        public void updateST(Espace p) throws SQLException
    {
        String req= "update espace set NOM_ES= ? , ADRESSE_ES = ? ,EMAIL_ES=?,NUMTEL_ES=?,FACEBOOK_ES=?,LOGO_ES=?,TYPE_ES=? where ID_ESPACE= ?";
        PreparedStatement  pst = con.prepareStatement(req);
      //  pst = con .prepareStatement(req);
        pst.setString(1, p.getNomEspace());
        pst.setString(2, p.getADRESSE_ES());
         pst.setString(3, p.getEMAIL_ES());
         pst.setInt(4, p. getNUMTEL_ES());
         pst.setString(5, p.getFACEBOOK_ES());
         pst.setString(6, p.getLOGO_ES());
         pst.setString(7, p.getTYPE_ES());
         
         
         
         
         
        pst.setInt(8, p.getID_ESPACE());
       // ste = connexion.createStatement();
        pst.executeUpdate();
        
    }
    
}
