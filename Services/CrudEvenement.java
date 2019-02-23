/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import connexion.DataSource;
import controller.LoginGUIController;
import entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import javax.print.attribute.standard.DateTimeAtCompleted;

/**
 *
 * @author koussai
 */
public class CrudEvenement {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
ResultSet rs ; 
    

 public Evenement EvenementByID(int id) throws SQLException{
       String requete="SELECT * FROM evenement Where ID_EVENEMENT = " + id +" ORDER BY DATEDEBUT_E DESC" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
       Evenement E =new Evenement() ;
        while(rs.next()){                   
         E = new Evenement(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getString(8),rs.getInt(9),rs.getFloat(10),rs.getString(11),rs.getString(12),rs.getString(13));
        
        }

        return E ;
    }



 public void InsertEvenement(Evenement E) throws SQLException
    {    
       

        
        
        
        String req="INSERT INTO evenement( ID_UTILISATEUR, TITRE_E, DESCRIPTION_E, AFFICHE_E, DATEDEBUT_E, DATEFIN_E, LIEU_E, DUREE_E, FRAIS_E, ORGANISATEUR_E, CONTACT_E, TYPE_E) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" ; 
        
        pst = cnx.prepareStatement(req);//attente des parametre
        
        pst.setInt(1, LoginGUIController.CurrentUser.getId() );    
        pst.setString(2, E.getTITRE_E()); 
        pst.setString(3, E.getDESCRIPTION_E());
        pst.setString(4, E.getAFFICHE_E());
        pst.setDate(5, E.getDATEDEBUT_E());
        pst.setDate(6,  E.getDATEFIN_E());
        pst.setString(7, E.getLIEU_E());
        pst.setInt(8, E.getDUREE_E());
        pst.setFloat(9, E.getFRAIS_E());
        pst.setString(10, E.getORGANISATEUR_E());
        pst.setString(11, E.getCONTACT_E());
        pst.setString(12, E.getTYPE_E());
        pst.executeUpdate();
        System.out.println(pst);
    }

 public void UpdateEvenement(Evenement E,int id) throws SQLException{
        String requete="UPDATE evenement SET TITRE_E=? ,DESCRIPTION_E=? ,AFFICHE_E=? ,DATEDEBUT_E=? ,DATEFIN_E=? ,LIEU_E=? ,DUREE_E=? ,FRAIS_E=? ,ORGANISATEUR_E=? ,CONTACT_E=? ,TYPE_E=?  where ID_EVENEMENT='"+id+"'"  ;
      
        pst = cnx.prepareStatement(requete);//attente des parametre

        pst.setString(1, E.getTITRE_E());
        
        pst.setString(2, E.getDESCRIPTION_E());
        
        pst.setString(3, E.getAFFICHE_E());
        
        pst.setDate(4, E.getDATEDEBUT_E());
      
        pst.setDate(5,  E.getDATEFIN_E());
       
        pst.setString(6, E.getLIEU_E());
       
        pst.setInt(7, E.getDUREE_E());
       
        pst.setFloat(8, E.getFRAIS_E());
        
        pst.setString(9, E.getORGANISATEUR_E());
        
        pst.setString(10, E.getCONTACT_E());
        
        pst.setString(11, E.getTYPE_E());
        
        
       
        
        System.out.println(pst);
         pst.executeUpdate();
        
       
    }
 
 public void DeleteEvenement(int id) throws SQLException
    {
        String req="Delete from evenement where ID_EVENEMENT="+id; 
        ste=cnx.createStatement();  
        ste.executeUpdate(req);    
    }
    
    public ObservableList<Evenement> displayAllEvenement() throws SQLException{
        String requete="SELECT * FROM evenement "+" ORDER BY DATEDEBUT_E DESC" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        ObservableList<Evenement> list =FXCollections.observableArrayList(); 
        while(rs.next()){                   
        Evenement E = new Evenement(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getString(8),rs.getInt(9),rs.getFloat(10),rs.getString(11),rs.getString(12),rs.getString(13));
        list.add(E) ;
        }
        
        return list ;
    }
    
    
        public ObservableList<Evenement> displayFiltreEvenement(String Type) throws SQLException{
        String requete="SELECT * FROM evenement Where TYPE_E = " +Type+" ORDER BY DATEDEBUT_E DESC" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        ObservableList<Evenement> list =FXCollections.observableArrayList(); 
        while(rs.next()){                   
        Evenement E = new Evenement(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getString(8),rs.getInt(9),rs.getFloat(10),rs.getString(11),rs.getString(12),rs.getString(13));
        list.add(E) ;
        }
        System.out.println(list);
        return list ;
    }

        public ObservableList<Evenement> Search(String Chercher) throws SQLException{
        String requete="SELECT * FROM evenement Where  TITRE_E LIKE '%"+Chercher +"%' ORDER BY DATEDEBUT_E DESC" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        ObservableList<Evenement> list =FXCollections.observableArrayList(); 
        while(rs.next()){                   
        Evenement E = new Evenement(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getString(8),rs.getInt(9),rs.getFloat(10),rs.getString(11),rs.getString(12),rs.getString(13));
        list.add(E) ;
        }
        System.out.println(list);
        return list ;
    } 
        
    }
    


